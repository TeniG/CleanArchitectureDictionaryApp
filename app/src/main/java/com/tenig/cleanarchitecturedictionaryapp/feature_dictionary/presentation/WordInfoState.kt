package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.presentation

import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.models.WordInfo

data class WordInfoState (
    val wordInfoList: List<WordInfo>  = emptyList(),
    val isLoading : Boolean = false
)
