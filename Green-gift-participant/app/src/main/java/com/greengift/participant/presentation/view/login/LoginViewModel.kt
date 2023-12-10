package com.greengift.participant.presentation.view.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greengift.participant.data.dto.LoginDTO
import com.greengift.participant.data.util.GreenDataStore
import com.greengift.participant.domain.use_case.user.Login
import com.greengift.participant.presentation.event.GreenGiftEvent
import com.greengift.participant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore: GreenDataStore,
    private val loginUseCase: Login
): ViewModel() {

    private val _loginDTO = mutableStateOf(LoginDTO())
    val loginDTO = _loginDTO

    private val _eventFlow = MutableSharedFlow<GreenGiftEvent>()
    val eventFlow = _eventFlow

    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.EnteredEmail -> {
                _loginDTO.value = _loginDTO.value.copy(
                    email = event.email
                )
            }
            is LoginEvent.EnteredPassword -> {
                _loginDTO.value = _loginDTO.value.copy(
                    password = event.password
                )
            }
            is LoginEvent.Login -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            if (isInvalid()) {
                _eventFlow.emit(GreenGiftEvent.ERROR("이메일과 패스워드는 비어있으면 안됩니다."))
                return@launch
            }
            loginUseCase(loginDTO.value).collect { response ->
                when(response){
                    is Resource.Success -> {
                        val token = response.data.let{ it ?: "error" }
                        dataStore.setToken(token)
                        _eventFlow.emit(GreenGiftEvent.LOGIN)
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

    private fun isInvalid() = loginDTO.value.email.isBlank() || loginDTO.value.password.isBlank()

}