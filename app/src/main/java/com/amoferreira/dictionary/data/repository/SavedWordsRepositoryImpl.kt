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

    override val savedWords: Flow<Resource<List<String>>>
        get() = _savedWords

    private val _savedWords: Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())

        try {
            Log.i(tag, "Getting saved words from DB.")
            dao.getAllWords().collect { savedWords ->
                Log.i(tag, "List of saved words from DB: $savedWords. Emitting them.")
                emit(Resource.Success(savedWords))
            }
        } catch (e: Exception) {
            Log.e(tag, "Couldn't get saved words from DB.", e)
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

    override suspend fun removeWord(word: String) {
        Log.i(tag, "Removing word from database.")
        dao.deleteWord(word)
    }
}