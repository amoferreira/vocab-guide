package com.amoferreira.dictionary.domain.repository

import com.amoferreira.dictionary.domain.model.WordInfo
import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow

interface SavedWordsRepository {

    fun getSavedWords(): Flow<Resource<List<String>>>
    suspend fun addWord(wordInfo: List<WordInfo>)
}