package com.greengift.participant.presentation.view.signup

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greengift.participant.data.dto.SignupDTO
import com.greengift.participant.domain.use_case.user.SignUp
import com.greengift.participant.presentation.event.GreenGiftEvent
import com.greengift.participant.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignUp
): ViewModel() {

    private val _signupDTO = mutableStateOf(SignupDTO("", "", "", "", "participant"))
    val signupDTO = _signupDTO

    private val _eventFlow = MutableSharedFlow<GreenGiftEvent>()
    val eventFlow = _eventFlow

    fun onEvent(event: SignupEvent){
        when(event){
            is SignupEvent.EnteredUsername  -> {
                _signupDTO.value = _signupDTO.value.copy(
                    username = event.username
                )
            }
            is SignupEvent.EnteredEmail -> {
                _signupDTO.value = _signupDTO.value.copy(
                    email = event.email
                )
            }
            is SignupEvent.EnteredPassword -> {
                _signupDTO.value = _signupDTO.value.copy(
                    password = event.password
                )
            }
            is SignupEvent.EnteredPassword2 -> {
                _signupDTO.value = _signupDTO.value.copy(
                    password2 = event.password2
                )
            }
            SignupEvent.Signup -> {
                signup()
            }
        }
    }

    private fun signup() {
        viewModelScope.launch {
            if (isInvalid()) {
                _eventFlow.emit(GreenGiftEvent.ERROR("이메일과 패스워드는 비어있으면 안됩니다."))
                return@launch
            }
            if (isPasswordWrong()){
                _eventFlow.emit(GreenGiftEvent.ERROR("패스워드를 확인해주세요."))
                return@launch
            }
            signupUseCase(signupDTO.value).collect { response ->
                when(response){
                    is Resource.Success -> {
                        _eventFlow.emit(GreenGiftEvent.SIGNUP)
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

    private fun isPasswordWrong() = (signupDTO.value.password != signupDTO.value.password2)
    private fun isInvalid() = signupDTO.value.email.isBlank() || signupDTO.value.password.isBlank()
            || signupDTO.value.password2.isBlank() || signupDTO.value.username.isBlank() || signupDTO.value.role.isBlank()

}