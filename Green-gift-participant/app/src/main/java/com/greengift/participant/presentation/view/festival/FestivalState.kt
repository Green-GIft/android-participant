package com.greengift.participant.presentation.view.festival

import com.greengift.participant.data.dto.FestivalAllDTO

data class FestivalState(
    val isLoading: Boolean = false,
    val point: Long = 0,
    val grade: String = "",
    val festivalAllDTO: List<FestivalAllDTO> = emptyList(),
    val error: String = ""
)
