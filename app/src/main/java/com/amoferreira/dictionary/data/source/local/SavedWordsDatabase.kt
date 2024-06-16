package com.amoferreira.dictionary.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amoferreira.dictionary.data.source.local.dao.SavedWordsDao
import com.amoferreira.dictionary.data.source.local.entity.SavedWordEntity

@Database(
    entities = [SavedWordEntity::class],
    version = 1
)
abstract class SavedWordsDatabase: RoomDatabase() {

    abstract val dao: SavedWordsDao
}