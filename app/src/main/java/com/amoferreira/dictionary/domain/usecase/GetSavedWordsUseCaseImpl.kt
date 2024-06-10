package com.amoferreira.dictionary.domain.usecase

import com.amoferreira.dictionary.domain.model.WordInfo
import com.amoferreira.dictionary.domain.repository.SavedWordsRepository
import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetSavedWordsUseCaseImpl(
    private val repository: SavedWordsRepository
) : GetSavedWordsUseCase {

    override val savedWords: Flow<Resource<List<String>>> = repository.getSavedWords()

    override suspend fun addWord(wordInfo: List<WordInfo>) {
        repository.addWord(wordInfo)
    }
}