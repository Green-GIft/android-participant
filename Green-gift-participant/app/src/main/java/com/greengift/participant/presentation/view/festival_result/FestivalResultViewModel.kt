package com.greengift.participant.presentation.view.festival_result

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greengift.participant.domain.use_case.festival.GetFestivalResult
import com.greengift.participant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FestivalResultViewModel @Inject constructor(
    private val getFestivalResultUseCase: GetFestivalResult,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val festivalId = savedStateHandle.get<Long>("festivalId") ?: -1

    private val _state = mutableStateOf(FestivalResultState())
    val state = _state

    init {
        getFestivalResult()
    }

    private fun getFestivalResult() {
        viewModelScope.launch {
            getFestivalResultUseCase(festivalId).collect {response ->
                when (response){
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isEmpty = (response.data != null),
                            festivalResult = response.data,
                            error = "",
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = response.message
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true,
                            error = ""
                        )
                    }
                }
            }
        }
    }

}