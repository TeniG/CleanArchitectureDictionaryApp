package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tenig.cleanarchitecturedictionaryapp.core.util.Resource
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.usecase.GetWordInfoUsecase
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.presentation.components.WordInfoScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
    val getWordInfoUsecase: GetWordInfoUsecase
) : ViewModel() {

    private val _wordInfoState = mutableStateOf(WordInfoState())
    val wordInfoState: State<WordInfoState> = _wordInfoState

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow: SharedFlow<UIEvent> = _eventFlow.asSharedFlow()

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }

    private var searchJob: Job? = null

    fun onEvent(event: WordInfoEvent) {
        when (event) {
            is WordInfoEvent.EnteredSearchText -> {
                _searchQuery.value = event.searchText

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getWordInfoUsecase(event.searchText).onEach { result ->
                        when (result) {
                            is Resource.Loading -> {
                                _wordInfoState.value = _wordInfoState.value.copy(
                                    wordInfoList = result.data ?: emptyList(),
                                    isLoading = true
                                )
                            }
                            is Resource.Success -> {
                                _wordInfoState.value = _wordInfoState.value.copy(
                                    wordInfoList = result.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                            is Resource.Error -> {
                                _wordInfoState.value = _wordInfoState.value.copy(
                                    wordInfoList = result.data ?: emptyList(),
                                    isLoading = false
                                )
                                _eventFlow.emit(
                                    UIEvent.ShowSnackbar(
                                        message = result.message ?: "Unknown Error"
                                    )
                                )
                            }
                        }

                    }.launchIn(this)
                }
            }
            is WordInfoEvent.ClearText -> {
                _wordInfoState.value = WordInfoState()
                _searchQuery.value = ""
            }
        }
    }


}