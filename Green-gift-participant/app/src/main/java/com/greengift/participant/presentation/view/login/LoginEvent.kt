package com.greengift.participant.presentation.view.login

sealed class LoginEvent {
    data class EnteredEmail(val email: String): LoginEvent()
    data class EnteredPassword(val password: String): LoginEvent()
    object Login: LoginEvent()
}