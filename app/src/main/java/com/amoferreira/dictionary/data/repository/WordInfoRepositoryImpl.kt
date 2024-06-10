package com.amoferreira.dictionary.data.repository

import android.content.Context
import android.util.Log
import com.amoferreira.dictionary.R
import com.amoferreira.dictionary.data.source.local.dao.WordInfoDao
import com.amoferreira.dictionary.data.source.remote.DictionaryApi
import com.amoferreira.dictionary.domain.model.WordInfo
import com.amoferreira.dictionary.domain.repository.WordInfoRepository
import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
    private val context: Context,
    private val api: DictionaryApi,
    private val dao: WordInfoDao
): WordInfoRepository {
    val TAG = javaClass.simpleName

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfo = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Loading(wordInfo))

        try {
            Log.i(TAG, "Getting word info from API for word $word.")
            val remoteWordInfo = api.getWordInfo(word)
            Log.i(TAG, "Got word info from API for word $word.")
            Log.i(TAG, "Deleting and inserting word info in DB for word $word.")
            dao.deleteWordInfo(remoteWordInfo.map { it.word })
            dao.insertWordInfo(remoteWordInfo.map { it.toWordInfoEntity() })
        } catch(e: HttpException) {
            Log.e(TAG, "HTTP Exception while getting info from API for word $word.", e)
            emit(Resource.Error(
                message = context.getString(R.string.error_message_http_exception)
            ))
        } catch(e: IOException) {
            Log.e(TAG, "IO Exception while getting info from API for word $word.", e)
            emit(Resource.Error(
                message = context.getString(R.string.error_message_http_exception)
            ))
        }

        Log.i(TAG, "Getting word info from DB for word $word.")
        val newWordInfo = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfo))
    }
}