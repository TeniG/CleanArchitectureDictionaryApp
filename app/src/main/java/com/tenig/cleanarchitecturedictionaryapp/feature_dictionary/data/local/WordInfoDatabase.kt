package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class WordInfoDatabase: RoomDatabase() {

    abstract val wordInfoDao : WordInfoDao
}