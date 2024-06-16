package com.amoferreira.dictionary.data.repository

import android.util.Log
import com.amoferreira.dictionary.data.source.local.dao.SavedWordsDao
import com.amoferreira.dictionary.data.source.local.entity.SavedWordEntity
import com.amoferreira.dictionary.domain.repository.SavedWordsRepository
import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SavedWordsRepositoryImpl(
    private val dao: SavedWordsDao
) : SavedWordsRepository {
    private val tag = javaClass.simpleName
    override fun getSavedWords(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())

        try {
            val savedWords = dao.getAllWords()
            emit(Resource.Success(savedWords))
        } catch (e: Exception) {
            emit(
                Resource.Error(
                    message = "Something went wrong"
                )
            )
        }
    }

    override suspend fun addWord(word: String) {
        Log.i(tag, "Adding word to database.")
        val savedWordEntity = SavedWordEntity(word)
        dao.insertWord(savedWordEntity)
    }
}