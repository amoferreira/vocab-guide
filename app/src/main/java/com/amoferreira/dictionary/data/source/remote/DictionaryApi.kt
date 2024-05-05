package com.amoferreira.dictionary.data.source.remote

import com.amoferreira.dictionary.data.source.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word: String
    ): List<WordInfoDto>

    companion object {
        val BASE_URL = "https://api.dictionaryapi.dev/"
    }
}