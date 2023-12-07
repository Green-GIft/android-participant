package com.greengift.participant.presentation.view.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.greengift.participant.R
import com.greengift.participant.presentation.component.GreenButton
import com.greengift.participant.presentation.component.GreenTextField
import com.greengift.participant.presentation.component.addFocusCleaner
import com.greengift.participant.presentation.event.GreenGiftEvent
import com.greengift.participant.presentation.navigation.Screen
import com.greengift.participant.ui.theme.main_green
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
){
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is GreenGiftEvent.LOGIN -> {
                    navController.navigate(Screen.FestivalScreen.route)
                }
                is GreenGiftEvent.ERROR -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is GreenGiftEvent.LOADING -> {}
                else -> {}
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
    ){
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.gift),
                contentDescription = "app_logo",
                modifier = Modifier
                    .padding(bottom = 45.dp)
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )
            GreenTextField(
                text = viewModel.loginDTO.value.email,
                onValueChange = {viewModel.onEvent(LoginEvent.EnteredEmail(it))},
                placeholder = "아이디 (이메일)",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            GreenTextField(
                text = viewModel.loginDTO.value.password,
                onValueChange = {viewModel.onEvent(LoginEvent.EnteredPassword(it))},
                placeholder = "패스워드",
                visualTransformation = PasswordVisualTransformation()
            )
            GreenButton(
                text = "로그인",
                onClick = {viewModel.onEvent(LoginEvent.Login)},
                modifier = Modifier.padding(top = 30.dp)
            )
            GreenButton(
                text = "회원가입",
                color = Color.White,
                textColor = main_green,
                onClick = {navController.navigate(Screen.SignupScreen.route)}
            )
        }

    }
}
