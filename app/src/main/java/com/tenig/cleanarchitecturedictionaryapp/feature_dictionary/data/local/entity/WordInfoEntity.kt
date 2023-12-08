package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.models.Meaning
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.models.WordInfo

@Entity(tableName = "word_info")
data class WordInfoEntity(
    @PrimaryKey val id: Int? = null,
    val word: String,
    val meanings: List<Meaning>,
    val phonetic: String
) {
    //To UI Model
    fun toWordInfo(): WordInfo {
        return WordInfo(
            word = word,
            meanings = meanings,
            phonetic = phonetic,
        )
    }
}
