package com.amoferreira.dictionary.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amoferreira.dictionary.domain.usecase.GetWordInfoUseCase
import com.amoferreira.dictionary.domain.usecase.SavedWordsUseCase
import com.amoferreira.dictionary.presentation.event.UIEvent
import com.amoferreira.dictionary.presentation.state.WordInfoState
import com.amoferreira.dictionary.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchWordInfoViewModel @Inject constructor(
    private val getWordInfoUseCase: GetWordInfoUseCase,
    private val savedWordsUseCase: SavedWordsUseCase
) : ViewModel() {
    private val tag = javaClass.simpleName

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _state = mutableStateOf(WordInfoState())
    val state: State<WordInfoState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DELAY)
            getWordInfoUseCase.getWordInfo(query)
                .onEach { result ->
                    Log.i(tag, "Received word info: $result")
                    when(result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = false
                            )
                            _eventFlow.emit(
                                UIEvent.ShowSnackbar(
                                result.message ?: "Unknown error"
                            ))
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                wordInfoItems = result.data ?: emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    fun onAddButtonClicked() {
        viewModelScope.launch {
            savedWordsUseCase.addWord(state.value.wordInfoItems[0].word)
        }
    }

    companion object {
        const val SEARCH_DELAY = 500L
    }
}