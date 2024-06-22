package com.amoferreira.dictionary.presentation.ui.containers

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.amoferreira.dictionary.presentation.ui.screen.SavedWordsScreen
import com.amoferreira.dictionary.presentation.viewmodel.SavedWordsViewModel

@Composable
fun SavedWordsContainer(scaffoldSnackbarHostState: SnackbarHostState) {
    val viewModel: SavedWordsViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    SavedWordsScreen(
        uiState = uiState.value,
        eventFlow = viewModel.eventFlow,
        scaffoldSnackbarHostState = scaffoldSnackbarHostState,
        onDeleteOptionClick = viewModel::deleteWord
    )
}