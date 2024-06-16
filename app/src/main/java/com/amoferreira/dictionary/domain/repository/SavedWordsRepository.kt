package com.amoferreira.dictionary.domain.repository

import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SavedWordsRepository {

    fun getSavedWords(): Flow<Resource<List<String>>>
    suspend fun addWord(word: String)
}