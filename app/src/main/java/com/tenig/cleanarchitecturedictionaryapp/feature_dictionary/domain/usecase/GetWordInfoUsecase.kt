package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.usecase

import com.tenig.cleanarchitecturedictionaryapp.core.util.Resource
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.models.WordInfo
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUsecase(private  val repository: WordInfoRepository) {

    operator fun invoke(word: String) : Flow<Resource<List<WordInfo>>> {
        if (word.isBlank()) {
            return flow {  }
        }
        return repository.getWordInfo(word)
    }

}