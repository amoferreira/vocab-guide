package com.amoferreira.dictionary.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amoferreira.dictionary.domain.usecase.SavedWordsUseCase
import com.amoferreira.dictionary.presentation.state.SavedWordsState
import com.amoferreira.dictionary.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedWordsViewModel @Inject constructor(
    private val savedWordsUseCase: SavedWordsUseCase
) : ViewModel()  {
    private val tag = javaClass.simpleName

    val uiState: StateFlow<SavedWordsState> = savedWordsUseCase.savedWords.map {
        val isLoading = it is Resource.Loading
        SavedWordsState(
            savedWords = it.data,
            isLoading = isLoading
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        SavedWordsState()
    )

    fun deleteWord(word: String) {
        viewModelScope.launch {
            Log.i(tag, "Removing word.")
            savedWordsUseCase.removeWord(word)
        }
    }
}