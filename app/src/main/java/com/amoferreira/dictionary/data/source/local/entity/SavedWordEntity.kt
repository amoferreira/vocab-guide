package com.amoferreira.dictionary.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SavedWordEntity(
    val word: String,
    @PrimaryKey val id: Int? = null
)