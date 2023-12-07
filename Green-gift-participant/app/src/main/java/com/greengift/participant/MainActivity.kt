package com.greengift.participant

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.datastore.preferences.preferencesDataStore
import com.greengift.participant.ui.theme.GreenGiftTheme
import dagger.hilt.android.AndroidEntryPoint

val Context.dataStore by preferencesDataStore("green_gift_data_store")

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { GreenGiftTheme() }
    }
}