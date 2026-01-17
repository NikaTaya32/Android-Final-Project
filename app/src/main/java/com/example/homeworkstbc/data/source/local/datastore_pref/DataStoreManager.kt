package com.example.homeworkstbc.data.source.local.datastore_pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DataStoreManager @Inject constructor(private val dataStore: DataStore<Preferences>) {
    fun isUserAuthorized(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            !preferences[DataStoreKeys.USER_ID].isNullOrEmpty()
        }
    }

    suspend fun <T> saveValue(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    fun <T> getValue(key: Preferences.Key<T>): Flow<T?> {
        return dataStore.data
            .map { preferences ->
                preferences[key]
            }
    }
    suspend fun <T> removeKey(key: Preferences.Key<T>) {
        dataStore.edit { preferences ->
            preferences.remove(key)
        }
    }
}

