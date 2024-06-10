package com.amoferreira.dictionary.domain.usecase

import android.util.Log
import com.amoferreira.dictionary.domain.model.WordInfo
import com.amoferreira.dictionary.domain.repository.WordInfoRepository
import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCaseImpl(
    private val repository: WordInfoRepository
): GetWordInfoUseCase {
    val TAG = javaClass.simpleName

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()) {
            Log.i(TAG, "Word is blank. Returning empty flow.")
            return flow {}
        }
        Log.i(TAG, "Getting word info for word $word.")
        val wordInfo = repository.getWordInfo(word)
        Log.i(TAG, "Got word info for word $word.")
        return wordInfo
    }
}