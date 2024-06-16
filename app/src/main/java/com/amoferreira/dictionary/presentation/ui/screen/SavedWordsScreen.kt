package com.amoferreira.dictionary.presentation.ui.screen

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.amoferreira.dictionary.R
import com.amoferreira.dictionary.presentation.state.SavedWordsState
import com.amoferreira.dictionary.presentation.ui.molecules.SavedWordsItem

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SavedWordsScreen(
    uiState: SavedWordsState,
    onDeleteOptionClick: (String) -> Unit
) {
    val wordsSize = uiState.savedWords?.size
    var contextMenuWord by rememberSaveable {
        mutableStateOf<String?>(null)
    }
    val haptics = LocalHapticFeedback.current
    val context = LocalContext.current
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
                    Text(text = stringResource(id = R.string.saved_words_title))
                }
                items(wordsSize) { index ->
                    val word = uiState.savedWords[index]
                    if (index > 0) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    SavedWordsItem(
                        word = word,
                        onClickAction = { /*TODO: Open word info action*/ },
                        onLongClickAction = {
                            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                            contextMenuWord = word
                        }
                    )
                    if (index < wordsSize - 1) {
                        Divider()
                    }
                }
            }
        } else {
            Text(text = stringResource(id = R.string.saved_words_empty_list_message))
        }
        if (uiState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        contextMenuWord?.let {
            val toastMessage = stringResource(id = R.string.delete_word_toast, it)
            ModalBottomSheet(
                onDismissRequest = { contextMenuWord = null }
            ) {
                ListItem(
                    headlineContent = { Text(stringResource(R.string.delete_word_option)) },
                    trailingContent = { Icon(Icons.Default.Delete, "Delete icon") },
                    modifier = Modifier.clickable {
                        onDeleteOptionClick(it)
                        Toast.makeText(
                            context,
                            toastMessage,
                            Toast.LENGTH_SHORT
                        ).show()
                        contextMenuWord = null
                    }
                )
            }
        }
    }
}
