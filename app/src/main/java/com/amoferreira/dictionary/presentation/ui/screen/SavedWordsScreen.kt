package com.amoferreira.dictionary.presentation.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.amoferreira.dictionary.presentation.state.SavedWordsState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedWordsScreen(
    uiState: SavedWordsState,
) {
    val wordsSize = uiState.savedWords?.size
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (wordsSize != null && wordsSize != 0) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                stickyHeader {
                    Text(text = "List of Words")
                }
                items(wordsSize) { index ->
                    val word = uiState.savedWords[index]
                    if (index > 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Text(text = word)
                    if (index < wordsSize - 1) {
                        Divider()
                    }
                }
            }
        } else {
            Text(text = "Add words to your list to see them here!")
        }
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

/*
@Preview
@Composable
fun ListScreenPreview() {
    WordsListScreen()
}*/
