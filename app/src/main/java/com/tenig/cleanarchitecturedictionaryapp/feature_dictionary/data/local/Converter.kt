package com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverters
import com.google.gson.reflect.TypeToken
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.data.local.util.JsonParser
import com.tenig.cleanarchitecturedictionaryapp.feature_dictionary.domain.models.Meaning

@ProvidedTypeConverter
class Converter(
    private val jsonParser: JsonParser
) {
    @TypeConverters
    fun fromMeaningJson(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverters
    fun toJson(meaningList: List<Meaning>): String {
        return jsonParser.toJson(
            meaningList,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
    }
}