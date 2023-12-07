package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.remote

import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteApi {
    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }

    @GET("api/v2/entries/en/{word}")
    fun getWordInfo(
        @Path("word") word: String
    ): List<WordInfoDto>
}