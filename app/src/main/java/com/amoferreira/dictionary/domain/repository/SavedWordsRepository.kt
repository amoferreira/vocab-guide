package com.amoferreira.dictionary.domain.repository

import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SavedWordsRepository {

    val savedWords: Flow<Resource<List<String>>>
    suspend fun addWord(word: String)
    suspend fun removeWord(word:String)
}