package com.greengift.participant.presentation.view.festival

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greengift.participant.domain.use_case.festival.GetFestivalAll
import com.greengift.participant.domain.use_case.user.GetGrade
import com.greengift.participant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FestivalViewModel  @Inject constructor(
    private val getGradeUseCase: GetGrade,
    private val getFestivalAllUseCase: GetFestivalAll,
): ViewModel() {

    private val _state = mutableStateOf(FestivalState())
    val state = _state

    private val _grade = mutableStateOf("")
    val grade = _grade

    private val _point = mutableLongStateOf(0)
    val point = _point

    init {
        getFestivalAll()
        getGrade()
    }
    private fun getFestivalAll() {
        viewModelScope.launch {
            getFestivalAllUseCase().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            festivalAllDTO = response.data.let { it ?: emptyList() }
                        )
                    }
                    is Resource.Error -> saveError(response.message)
                    is Resource.Loading -> loading()
                }
            }
        }
    }

    private fun getGrade() {
        viewModelScope.launch {
            getGradeUseCase().collect {response ->
                when(response){
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            error = ""
                        )
                        _grade.value = response.data?.grade ?: ""
                        _point.longValue = response.data?.mileage ?: 0
                    }
                    is Resource.Error -> saveError(response.message)
                    is Resource.Loading -> loading()
                }
            }
        }
    }

    private fun saveError(message: String?) {
        _state.value = _state.value.copy(
            isLoading = false,
            error = message ?: "예상하지 못한 에러입니다."
        )
    }
    private fun loading() {
        _state.value = _state.value.copy(
            isLoading = true
        )
    }

}