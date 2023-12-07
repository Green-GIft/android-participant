package com.greengift.participant.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class LoginDTO(
    val email: String,
    val password: String,
)
