package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.dto

import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity

data class WordInfoDto(
    val word: String,
    val meanings: List<MeaningDto>,
    val phonetic: String?,
    val sourceUrls: List<String>,
    val license: LicenseDto,
    val phonetics: List<PhoneticDto>
) {
    //To database Model
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            word = word,
            meanings = meanings.map { it.toMeaning() },
            phonetic = phonetic ?: "",
        )
    }
}