package com.greengift.participant.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductParticipantDTO(
    val image: String = "",
    val name: String = "",
    val company: String = "",
    val category: String = ""
)
