package com.greengift.participant.presentation.view.festival_join

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greengift.participant.data.dto.FestivalJoinDTO
import com.greengift.participant.domain.use_case.festival.JoinFestival
import com.greengift.participant.presentation.event.GreenGiftEvent
import com.greengift.participant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FestivalJoinViewModel @Inject constructor(
    private val joinFestivalUseCase: JoinFestival
): ViewModel() {

    private val _festivalName = mutableStateOf(FestivalJoinDTO())
    val festivalName = _festivalName

    private val _eventFlow = MutableSharedFlow<GreenGiftEvent>()
    val eventFlow = _eventFlow


    fun onEvent(event: FestivalJoinEvent){
        when(event){
            is FestivalJoinEvent.EnteredFestivalName -> {
                _festivalName.value = _festivalName.value.copy(
                    name = event.name
                )
            }
            is FestivalJoinEvent.FestivalJoin -> {
                joinFestival()
            }
        }
    }

    private fun joinFestival() {
        viewModelScope.launch {
            joinFestivalUseCase(festivalName.value).collect { response ->
                when(response){
                    is Resource.Success -> {
                        _eventFlow.emit(GreenGiftEvent.JOIN)
                    }
                    is Resource.Error -> {
                        _eventFlow.emit(GreenGiftEvent.ERROR(response.message))
                    }
                    is Resource.Loading -> {
                        _eventFlow.emit(GreenGiftEvent.LOADING)
                    }
                }
            }
        }
    }
}