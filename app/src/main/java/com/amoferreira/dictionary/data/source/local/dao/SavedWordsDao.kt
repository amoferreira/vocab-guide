package com.amoferreira.dictionary.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amoferreira.dictionary.data.source.local.entity.WordInfoEntity

@Dao
interface SavedWordsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedWord(wordInfo: List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
    suspend fun deleteWordInfo(words: List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordInfo(word: String): List<WordInfoEntity>

    @Query("SELECT word FROM wordinfoentity")
    suspend fun getAllWords(): List<String>
}