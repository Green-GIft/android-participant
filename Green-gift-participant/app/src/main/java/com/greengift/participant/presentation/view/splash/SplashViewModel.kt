package com.greengift.participant.presentation.view.splash

import androidx.lifecycle.ViewModel
import com.greengift.participant.data.util.GreenDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val dataStore: GreenDataStore
): ViewModel() {

    fun isUserLoggedIn(): Boolean{
        val token = dataStore.getToken()
        return token.isNotBlank()
    }

}