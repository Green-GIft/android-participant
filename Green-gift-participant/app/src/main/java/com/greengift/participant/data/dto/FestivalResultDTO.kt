package com.greengift.participant.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class FestivalResultDTO(
    val image: String = "",
    val name: String = "",
    val dueDate: String = ""
)