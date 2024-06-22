package com.amoferreira.dictionary.presentation.ui.containers

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.amoferreira.dictionary.presentation.ui.screen.SearchScreen
import com.amoferreira.dictionary.presentation.viewmodel.SearchWordInfoViewModel

@Composable
fun SearchContainer(scaffoldSnackbarHostState: SnackbarHostState) {
    val viewModel: SearchWordInfoViewModel = hiltViewModel()
    SearchScreen(
        uiState = viewModel.state,
        eventFlow = viewModel.eventFlow,
        scaffoldSnackbarHostState = scaffoldSnackbarHostState,
        searchQuery = viewModel.searchQuery,
        onSearchAction = viewModel::onSearch,
        onAddButtonClick = viewModel::onAddButtonClicked
    )
}