package com.amoferreira.dictionary.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amoferreira.dictionary.domain.model.Meaning
import com.amoferreira.dictionary.domain.model.WordInfo

@Entity
data class WordInfoEntity(
    val meanings: List<Meaning>,
    val phonetic: String?,
    val word: String,
    @PrimaryKey val id: Int? = null
) {
    fun toWordInfo(): WordInfo {
        return WordInfo(
            meanings = meanings,
            phonetic = phonetic,
            word = word
        )
    }
}
