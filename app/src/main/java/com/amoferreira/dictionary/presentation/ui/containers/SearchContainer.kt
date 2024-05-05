package com.amoferreira.dictionary.presentation.ui.containers

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.amoferreira.dictionary.presentation.ui.screen.SearchScreen
import com.amoferreira.dictionary.presentation.viewmodel.SearchWordInfoViewModel

@Composable
fun SearchContainer() {
    val viewModel: SearchWordInfoViewModel = hiltViewModel()
    SearchScreen(
        uiState = viewModel.state,
        eventFlow = viewModel.eventFlow,
        searchQuery = viewModel.searchQuery,
        onSearchAction = viewModel::onSearch,
    )
}