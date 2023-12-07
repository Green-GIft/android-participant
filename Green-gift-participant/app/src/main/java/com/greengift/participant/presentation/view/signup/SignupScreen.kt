package com.greengift.participant.presentation.view.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.greengift.participant.presentation.component.GreenTextField
import com.greengift.participant.presentation.component.GreenTitleWithButton
import com.greengift.participant.presentation.component.addFocusCleaner
import com.greengift.participant.presentation.event.GreenGiftEvent
import com.greengift.participant.presentation.navigation.Screen
import com.greengift.participant.ui.theme.typography
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignupScreen(
    navController: NavController,
    viewModel: SignupViewModel = hiltViewModel()
){
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is GreenGiftEvent.SIGNUP -> {
                    navController.navigate(Screen.LoginScreen.route)
                }
                is GreenGiftEvent.ERROR -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is GreenGiftEvent.LOADING -> {}
                else -> {}
            }
        }
    }
    GreenTitleWithButton(
        modifier = Modifier.addFocusCleaner(focusManager),
        title = "회원가입",
        buttonText = "회원가입 완료",
        onButtonClick = {viewModel.onEvent(SignupEvent.Signup)}
    ) {
        GetInformation(
            title = "닉네임",
            text = viewModel.signupDTO.value.username,
            onValueChanged = {viewModel.onEvent(SignupEvent.EnteredUsername(it))},
            placeholder = "홍길동"
        )
        GetInformation(
            title = "이메일",
            text = viewModel.signupDTO.value.email,
            onValueChanged = {viewModel.onEvent(SignupEvent.EnteredEmail(it))},
            placeholder = "hello@gmail.com",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        GetInformation(
            title = "패스워드",
            text = viewModel.signupDTO.value.password,
            onValueChanged = {viewModel.onEvent(SignupEvent.EnteredPassword(it))},
            visualTransformation = PasswordVisualTransformation()
        )
        GetInformation(
            title = "패스워드 확인",
            text = viewModel.signupDTO.value.password2,
            onValueChanged = {viewModel.onEvent(SignupEvent.EnteredPassword2(it))},
            visualTransformation = PasswordVisualTransformation()
        )
    }
    
}

@Composable
fun GetInformation(
    title: String,
    text: String,
    onValueChanged: (String) -> Unit = {},
    placeholder: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Row(
        modifier = Modifier.padding(top = 25.dp, bottom = 5.dp)
    ) {
        Text(text = title, style = typography.displayLarge)
        Text(text = " *", style = typography.displayLarge, color = Color.Red)
    }
    GreenTextField(
        text = text,
        onValueChange = onValueChanged,
        placeholder = placeholder,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation
    )
}