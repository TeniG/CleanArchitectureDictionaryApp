package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.dto

data class PhoneticDto(
    val audio: String,
    val license: LicenseDto,
    val sourceUrl: String,
    val text: String
)