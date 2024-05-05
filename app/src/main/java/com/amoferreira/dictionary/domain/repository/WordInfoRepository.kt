package com.amoferreira.dictionary.domain.repository

import com.amoferreira.dictionary.domain.model.WordInfo
import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}