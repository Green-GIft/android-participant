package com.greengift.participant.presentation.view.festival_result

import com.greengift.participant.data.dto.FestivalResultDTO

data class FestivalResultState (
    val isLoading: Boolean = false,
    val isEmpty: Boolean = false,
    val festivalResult: FestivalResultDTO? = null,
    val error: String = ""
)