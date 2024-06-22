package com.amoferreira.dictionary.presentation.event

sealed class UIEvent {
    /*data class ShowSnackbar(val message: String): UIEvent()*/
    data class ShowSnackbar(
        val message: String,
        val actionLabel: String? = null,
        val action: (() -> Unit)? = null,
        val dismissed: (() -> Unit)? = null
    ) :  UIEvent()
}