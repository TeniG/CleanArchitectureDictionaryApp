package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.models.WordInfo

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = wordInfo.word,
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = wordInfo.phonetic,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(16.dp))

        wordInfo.meanings.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            meaning.definitions.forEachIndexed { i, definitions ->
                Text(text = "${i + 1} . ${definitions.definition}")
                Spacer(modifier = Modifier.height(8.dp))
                definitions.example?.let {
                    Text(text = "Example: ${definitions.example}")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}
