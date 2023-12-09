package com.greengift.participant.presentation.view.festival_join

sealed class FestivalJoinEvent {
    data class EnteredFestivalName(val name: String): FestivalJoinEvent()
    object FestivalJoin: FestivalJoinEvent()
}