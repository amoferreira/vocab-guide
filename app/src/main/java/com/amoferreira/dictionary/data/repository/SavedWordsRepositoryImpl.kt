package com.amoferreira.dictionary.data.repository

import com.amoferreira.dictionary.data.source.local.dao.SavedWordsDao
import com.amoferreira.dictionary.domain.model.WordInfo
import com.amoferreira.dictionary.domain.repository.SavedWordsRepository
import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SavedWordsRepositoryImpl(
    private val dao: SavedWordsDao
) : SavedWordsRepository {
    override fun getSavedWords(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())

        try {
            val savedWords = dao.getAllWords().distinct()
            emit(Resource.Success(savedWords))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = "Something went wrong"
                )
            )
        }
    }

    override suspend fun addWord(wordInfo: List<WordInfo>) {
        val wordInfoEntity = wordInfo.map { it.toWordInfoEntity() }
        dao.insertSavedWord(wordInfoEntity)
    }
}