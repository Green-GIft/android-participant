package com.greengift.participant.presentation.event

sealed class GreenGiftEvent {
    object SIGNUP: GreenGiftEvent()
    object LOGIN: GreenGiftEvent()
    object JOIN: GreenGiftEvent()
    object BUY: GreenGiftEvent()

    object LOADING: GreenGiftEvent()
    data class ERROR(val message: String): GreenGiftEvent()
}