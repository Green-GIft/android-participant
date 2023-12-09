package com.greengift.participant.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class FestivalAllDTO(
    val festivalId: Long = 0,
    val image: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val name: String = "",
    val state: String = ""
)
