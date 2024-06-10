package com.amoferreira.dictionary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amoferreira.dictionary.domain.usecase.GetSavedWordsUseCase
import com.amoferreira.dictionary.presentation.state.SavedWordsState
import com.amoferreira.dictionary.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SavedWordsViewModel @Inject constructor(
    getSavedWordsUseCase: GetSavedWordsUseCase
) : ViewModel()  {

    val uiState: StateFlow<SavedWordsState> = getSavedWordsUseCase.savedWords.map {
        val isLoading = when(it) {
            is Resource.Success -> false
            is Resource.Error -> false
            is Resource.Loading -> false
        }
        SavedWordsState(
            savedWords = it.data,
            isLoading = isLoading
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        SavedWordsState()
    )

}