package com.amoferreira.dictionary.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amoferreira.dictionary.data.source.local.entity.SavedWordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedWordsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWord(word: SavedWordEntity)

    @Query("DELETE FROM savedwordentity WHERE word IN(:word)")
    suspend fun deleteWord(word: String)

    @Query("SELECT word FROM savedwordentity")
    fun getAllWords(): Flow<List<String>>
}