package com.greengift.participant.data.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.greengift.participant.dataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

const val TOKEN_KEY = "green-gift-token"

class GreenDataStore @Inject constructor(@ApplicationContext val context: Context) {
    private val store = context.dataStore
    fun setToken(value: String){
        runBlocking(Dispatchers.IO) {
            store.edit{ it[stringPreferencesKey(TOKEN_KEY)] = value }
        }
    }
    fun getToken(): String {
        return runBlocking(Dispatchers.IO){
            store.data.map{ it[stringPreferencesKey(TOKEN_KEY)] ?: "" }.first()
        }
    }
}