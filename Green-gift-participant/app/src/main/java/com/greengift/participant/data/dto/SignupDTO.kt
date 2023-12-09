package com.greengift.participant.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SignupDTO(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val password2: String = "",
    val role: String = ""
)
