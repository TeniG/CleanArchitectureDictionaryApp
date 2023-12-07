package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.models

data class WordInfo(
    val word: String,
    val meanings: List<Meaning>,
    val phonetic: String,
)