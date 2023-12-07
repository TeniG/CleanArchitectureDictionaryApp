package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.dto

import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.models.Definition

data class DefinitionDto(
    val antonyms: List<String>,
    val definition: String,
    val example: String?,
    val synonyms: List<String>
) {
    fun toDefinition() : Definition {
        return  Definition(
            antonyms = antonyms,
            definition = definition,
            example = example,
            synonyms = synonyms
        )
    }
}