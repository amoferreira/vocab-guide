package com.amoferreira.dictionary.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amoferreira.dictionary.R
import com.amoferreira.dictionary.domain.usecase.SavedWordsUseCase
import com.amoferreira.dictionary.presentation.event.UIEvent
import com.amoferreira.dictionary.presentation.state.SavedWordsState
import com.amoferreira.dictionary.utils.Resource
import com.amoferreira.dictionary.utils.ResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedWordsViewModel @Inject constructor(
    private val savedWordsUseCase: SavedWordsUseCase,
    private val resourceProvider: ResourceProvider
) : ViewModel() {
    private val tag = javaClass.simpleName

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

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
            val undoAction = CompletableDeferred<Boolean>()
            Log.i(tag, "Removing word.")
            _eventFlow.emit(
                UIEvent.ShowSnackbar(
                    message = resourceProvider.getString(R.string.delete_word_snackbar_message, word),
                    actionLabel = resourceProvider.getString(R.string.delete_word_snackbar_action_label),
                    action = {
                        Log.i(tag, "Snackbar undo clicked.")
                        undoAction.complete(true)
                    },
                    dismissed = {
                        Log.i(tag, "Snackbar dismissed.")
                        undoAction.complete(false)
                    }
                )
            )
            if (!undoAction.await()) {
                Log.i(tag, "Removing word.")
                savedWordsUseCase.removeWord(word)
            } else {
                Log.i(tag, "Word removal undone.")
            }
        }
    }
}