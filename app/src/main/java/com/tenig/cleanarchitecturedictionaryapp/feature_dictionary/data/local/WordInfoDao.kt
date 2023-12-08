package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordInfos(wordInfo: List<WordInfoEntity>)

    @Query("Delete from word_info where word IN(:words)")
    suspend fun deleteWordInfos(words: List<String>)

    @Query("Select * from word_info where word LIKE '%' || :word || '%'")
    suspend fun getWordInfos(word: String) : List<WordInfoEntity>
}