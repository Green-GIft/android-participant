package com.greengift.participant.presentation.view.gift

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greengift.participant.domain.use_case.product.GetProductParticipant
import com.greengift.participant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiftViewModel @Inject constructor(
    private val getProductParticipantUseCase: GetProductParticipant
): ViewModel() {
    private val _state = mutableStateOf(GiftState())
    val state = _state

    init {
        getMyGift()
    }

    private fun getMyGift() {
        viewModelScope.launch {
            getProductParticipantUseCase().collect { response ->
                when (response){
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            giftList = response.data.let { it ?: emptyList() },
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