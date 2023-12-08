package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.repository

import com.tenig.cleanarchitecturedictionaryapp.core.util.Resource
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.models.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String) : Flow<Resource<List<WordInfo>>>
}