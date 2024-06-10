package com.amoferreira.dictionary.presentation.state

data class SavedWordsState(
    val savedWords: List<String>? = emptyList(),
    val isLoading: Boolean = false
)