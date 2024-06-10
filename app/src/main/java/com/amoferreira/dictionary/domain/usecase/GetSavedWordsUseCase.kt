package com.amoferreira.dictionary.domain.usecase

import com.amoferreira.dictionary.domain.model.WordInfo
import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetSavedWordsUseCase {
    val savedWords: Flow<Resource<List<String>>>
    suspend fun addWord(wordInfo: List<WordInfo>)
}