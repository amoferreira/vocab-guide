package com.amoferreira.dictionary.presentation.ui.molecules

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SavedWordsItem(
    word: String,
    onClickAction: () -> Unit,
    onLongClickAction: () -> Unit
) {
    Row(
        modifier = Modifier
            .combinedClickable(
                onClick = onClickAction,
                onLongClick = onLongClickAction
            )
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Text(text = word)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Outlined.KeyboardArrowRight,
            contentDescription = "Go to icon"
        )
    }
}