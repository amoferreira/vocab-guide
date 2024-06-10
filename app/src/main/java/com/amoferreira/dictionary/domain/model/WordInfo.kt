package com.amoferreira.dictionary.domain.model

import com.amoferreira.dictionary.data.source.local.entity.WordInfoEntity

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String?,
    val word: String
) {
    fun toWordInfoEntity(): WordInfoEntity {
        return WordInfoEntity(
            meanings = meanings,
            phonetic = phonetic,
            word = word
        )
    }
}
