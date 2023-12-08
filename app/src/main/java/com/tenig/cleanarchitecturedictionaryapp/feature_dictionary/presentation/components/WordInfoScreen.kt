package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.presentation.WordInfoEvent
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.presentation.WordInfoViewModel
import kotlinx.coroutines.flow.collectLatest


@Composable
fun WordInfoScreen() {
    val viewModel = hiltViewModel<WordInfoViewModel>()
    val state = viewModel.wordInfoState.value
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("Search...")
                    },
                    value = viewModel.searchQuery.value,
                    onValueChange = {
                        viewModel.onEvent(WordInfoEvent.EnteredSearchText(it))
                    },
                    trailingIcon = {

                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.wordInfoList.size) { index ->
                        val wordinfo = state.wordInfoList[index]
                        if (index > 0) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        WordInfoItem(wordInfo = wordinfo)
                        if (index < state.wordInfoList.size - 1) {
                            Divider()
                        }
                    }
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

    }
}