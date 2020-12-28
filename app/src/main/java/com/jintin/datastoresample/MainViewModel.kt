package com.jintin.datastoresample

import android.app.Application
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val dataStore = application.createDataStore(
        name = "testData"
    )

    fun increaseCounter() {
        viewModelScope.launch {
            dataStore.edit { preferences ->
                val value = preferences[PrefKey.KEY_COUNTER] ?: 0

                preferences[PrefKey.KEY_COUNTER] = value + 1
            }
        }
    }

    val getCounter: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[PrefKey.KEY_COUNTER] ?: -1
        }
}