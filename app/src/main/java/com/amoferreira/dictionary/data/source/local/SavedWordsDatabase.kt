package com.amoferreira.dictionary.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.amoferreira.dictionary.data.source.local.dao.SavedWordsDao
import com.amoferreira.dictionary.data.source.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class SavedWordsDatabase: RoomDatabase() {

    abstract val dao: SavedWordsDao
}