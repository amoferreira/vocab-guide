package com.amoferreira.dictionary.data.repository

import android.content.Context
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

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfo = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Loading(wordInfo))

        try {
            val remoteWordInfo = api.getWordInfo(word)
            dao.deleteWordInfo(remoteWordInfo.map { it.word })
            dao.insertWordInfo(remoteWordInfo.map { it.toWordInfoEntity() })
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = context.getString(R.string.error_message_http_exception)
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = context.getString(R.string.error_message_http_exception)
            ))
        }

        val newWordInfo = dao.getWordInfo(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfo))
    }
}