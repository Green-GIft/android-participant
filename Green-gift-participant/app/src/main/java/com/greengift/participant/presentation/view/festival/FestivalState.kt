package com.greengift.participant.presentation.view.festival

import com.greengift.participant.data.dto.FestivalAllDTO

data class FestivalState(
    val isLoading: Boolean = false,
    val festivalAllDTO: List<FestivalAllDTO> = emptyList(),
    val error: String = ""
)
