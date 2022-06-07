package com.example.studikasus7chapter8.datastore

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserLoginManager(context: Context) {
    private val dataStore: DataStore<Preferences> = context.createDataStore("login-prefs")

    companion object {
        val ADDRESS = preferencesKey<String>("ADDRESS")
        val NAME = preferencesKey<String>("NAME")
        val PASSWORD = preferencesKey<String>("PASSWORD")
        val UMUR = preferencesKey<Int>("UMUR")
        val USERNAME = preferencesKey<String>("USERNAME")

        val BOOLEAN = preferencesKey<Boolean>("BOOLEAN")
    }

    suspend fun saveDataLogin(
        address: String,
        name: String,
        password: String,
        umur: Int,
        username: String
    ) {
        dataStore.edit {
            it[ADDRESS] = address
            it[NAME] = name
            it[PASSWORD] = password
            it[UMUR] = umur
            it[USERNAME] = username
        }
    }

    suspend fun setBoolean(boolean: Boolean) {
        dataStore.edit {
            it[BOOLEAN] = boolean
        }
    }

//    suspend fun clearDataLogin() {
//        dataStore.edit {
//            it.clear()
//        }
//    }

    val name: Flow<String> = dataStore.data.map {
        it[NAME] ?: ""
    }

    val boolean: Flow<Boolean> = dataStore.data.map {
        it[BOOLEAN] ?: false
    }
}