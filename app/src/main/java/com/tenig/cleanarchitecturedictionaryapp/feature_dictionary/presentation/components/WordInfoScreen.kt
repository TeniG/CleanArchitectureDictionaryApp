package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.presentation.WordInfoEvent
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.presentation.WordInfoViewModel


@Composable
fun WordInfoScreen() {
    val viewModel = hiltViewModel<WordInfoViewModel>()
    val state = viewModel.wordInfoState.value
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        Box(
            modifier = Modifier.background(MaterialTheme.colors.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(value = viewModel.searchQuery.value, onValueChange = {
                    viewModel.onEvent(WordInfoEvent.EnteredSearchText(it))
                })
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.wordInfoList.size) { index ->
                        val wordinfo = state.wordInfoList[index]
                        if (index > 0) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        Text(text = wordinfo.word)
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