package com.amoferreira.dictionary.presentation.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.amoferreira.dictionary.presentation.state.WordInfoState
import com.amoferreira.dictionary.presentation.ui.molecules.WordInfoItem
import com.amoferreira.dictionary.presentation.viewmodel.SearchWordInfoViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(
    uiState: State<WordInfoState>,
    eventFlow: SharedFlow<SearchWordInfoViewModel.UIEvent>,
    searchQuery: State<String>,
    onSearchAction: (String) -> Unit,
    onAddButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scaffoldState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = true) {
        eventFlow.collectLatest { event ->
            when (event) {
                is SearchWordInfoViewModel.UIEvent.ShowSnackbar -> {
                    scaffoldState.showSnackbar(message = event.message)
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            stickyHeader {
                TextField(
                    value = searchQuery.value,
                    onValueChange = onSearchAction,
                    placeholder = { Text("Search...") },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search Icon"
                        )
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            val wordInfoItemsSize = uiState.value.wordInfoItems.size
            items(wordInfoItemsSize) { index ->
                val wordInfo = uiState.value.wordInfoItems[index]
                if (index > 0) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
                WordInfoItem(wordInfo = wordInfo)
                if (index < wordInfoItemsSize - 1) {
                    Divider()
                }
            }

        }
        IconButton(
            onClick = { onAddButtonClick() },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Icon"
            )
        }
        if (uiState.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}