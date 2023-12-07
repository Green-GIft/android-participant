package com.greengift.participant.presentation.view.signup

sealed class SignupEvent {
    data class EnteredEmail(val email: String): SignupEvent()
    data class EnteredUsername(val username: String): SignupEvent()
    data class EnteredPassword(val password: String): SignupEvent()
    data class EnteredPassword2(val password2: String): SignupEvent()

    object Signup: SignupEvent()
}