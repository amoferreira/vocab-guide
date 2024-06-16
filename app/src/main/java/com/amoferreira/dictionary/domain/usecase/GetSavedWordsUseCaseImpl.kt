package com.amoferreira.dictionary.domain.usecase

import android.util.Log
import com.amoferreira.dictionary.domain.repository.SavedWordsRepository
import com.amoferreira.dictionary.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetSavedWordsUseCaseImpl(
    private val repository: SavedWordsRepository
) : GetSavedWordsUseCase {
    private val tag = javaClass.simpleName

    override val savedWords: Flow<Resource<List<String>>> = repository.getSavedWords()

    override suspend fun addWord(word: String) {
        Log.i(tag, "Adding word to repository.")
        repository.addWord(word)
    }
}