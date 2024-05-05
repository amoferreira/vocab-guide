package com.amoferreira.dictionary.domain.usecase

import com.amoferreira.dictionary.domain.model.WordInfo
import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetWordInfoUseCase {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}