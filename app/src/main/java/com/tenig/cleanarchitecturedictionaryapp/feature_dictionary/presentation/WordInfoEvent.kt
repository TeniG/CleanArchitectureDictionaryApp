package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.presentation

sealed class WordInfoEvent {
    data class EnteredSearchText(val searchText: String) : WordInfoEvent()
    object ClearText : WordInfoEvent()
}