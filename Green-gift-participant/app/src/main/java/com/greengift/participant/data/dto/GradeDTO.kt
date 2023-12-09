package com.greengift.participant.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GradeDTO(
    val grade: String = "",
    val mileage: Long = 0
)