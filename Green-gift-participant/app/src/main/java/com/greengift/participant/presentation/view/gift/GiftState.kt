package com.greengift.participant.presentation.view.gift

import com.greengift.participant.data.dto.ProductParticipantDTO

data class GiftState(
    val isLoading: Boolean = false,
    val giftList: List<ProductParticipantDTO> = emptyList(),
    val error: String = ""
)