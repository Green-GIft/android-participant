package com.greengift.participant.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductAllDTO(
    val productId: Long = 0,
    val image: String = "",
    val name: String = "",
    val company: String = "",
    val price: Long = 0
)
