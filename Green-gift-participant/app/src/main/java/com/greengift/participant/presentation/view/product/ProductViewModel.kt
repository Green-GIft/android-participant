package com.greengift.participant.presentation.view.product

import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greengift.participant.data.util.GreenDataStore
import com.greengift.participant.domain.use_case.product.BuyProduct
import com.greengift.participant.domain.use_case.product.GetProductAll
import com.greengift.participant.domain.use_case.user.GetGrade
import com.greengift.participant.presentation.event.GreenGiftEvent
import com.greengift.participant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getGradeUseCase: GetGrade,
    private val getProductAllUseCase: GetProductAll,
    private val buyProductUseCase: BuyProduct,
    private val dataStore: GreenDataStore
): ViewModel(){

    private val _eventflow = MutableSharedFlow<GreenGiftEvent>()
    val eventflow = _eventflow

    private val _state = mutableStateOf(ProductState())
    val state = _state

    private val _grade = mutableStateOf("")
    val grade = _grade

    private val _point = mutableLongStateOf(0)
    val point = _point

    init {
        getProductAll()
        getGrade()
    }

    fun buyProduct(productId: Long, price: Long){
        viewModelScope.launch {
            buyProductUseCase(productId).collect { response ->
                when(response){
                    is Resource.Success -> {
                        _eventflow.emit(GreenGiftEvent.BUY)
                        _point.longValue = point.longValue - price
                    }
                    is Resource.Error -> {
                        _eventflow.emit(GreenGiftEvent.ERROR(response.message))
                    }
                    is Resource.Loading -> {
                        _eventflow.emit(GreenGiftEvent.LOADING)
                    }
                }
            }
        }
    }
    private fun getProductAll() {
        viewModelScope.launch {
            getProductAllUseCase().collect { response ->
                when(response){
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            productList = response.data.let { it ?: emptyList() },
                            error = "",
                            isLoading = false
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
            isLoading = true,
            error = ""
        )
    }
}