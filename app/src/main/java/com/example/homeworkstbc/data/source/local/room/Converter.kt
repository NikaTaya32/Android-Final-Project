package com.example.homeworkstbc.data.source.local.room

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json

class Converter {
    @TypeConverter
    fun fromStringList(value: String?): List<String>? {
        return value?.let { Json.decodeFromString<List<String>>(it) }
    }

    @TypeConverter
    fun toStringList(list: List<String>?) : String? {
        return list?.let { Json.encodeToString(it) }
    }
}