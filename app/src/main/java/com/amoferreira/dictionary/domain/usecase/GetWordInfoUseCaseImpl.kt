package com.amoferreira.dictionary.domain.usecase

import com.amoferreira.dictionary.domain.model.WordInfo
import com.amoferreira.dictionary.domain.repository.WordInfoRepository
import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWordInfoUseCaseImpl(
    private val repository: WordInfoRepository
): GetWordInfoUseCase {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> {
        if(word.isBlank()) {
            return flow {}
        }
        return repository.getWordInfo(word)
    }
}