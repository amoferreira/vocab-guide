package com.amoferreira.dictionary.presentation.ui.molecules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amoferreira.dictionary.domain.model.WordInfo

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = wordInfo.word,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
            fontSize = 32.sp
        )
        wordInfo.phonetic?.let  {
            Text(
                text = wordInfo.phonetic,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                fontSize = 24.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        wordInfo.meanings.forEach { meaning ->
            Text(
                text = meaning.partOfSpeech,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            meaning.definitions.forEachIndexed { index, definition ->
                Text(
                    text = "${index +1}. ${definition.definition}",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                definition.example?.let { example ->
                    Text(
                        text = example,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}