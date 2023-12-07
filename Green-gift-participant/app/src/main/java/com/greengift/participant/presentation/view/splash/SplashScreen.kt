package com.greengift.participant.presentation.view.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.greengift.participant.R
import com.greengift.participant.presentation.navigation.Screen
import com.greengift.participant.ui.theme.main_green
import com.greengift.participant.ui.theme.typography
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
){
    LaunchedEffect(key1 = true){
        val nextScreen = when (viewModel.isUserLoggedIn()) {
            true -> Screen.FestivalScreen
            false -> Screen.LoginScreen
        }
        delay(1000)
        navController.popBackStack()
        navController.navigate(nextScreen.route)
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.gift),
            contentDescription = "app_logo",
            modifier = Modifier.size(120.dp)
                .align(Alignment.Center)
        )
        Text(
            text = "Green Gift",
            style = typography.titleLarge,
            color = main_green,
            modifier = Modifier
                .padding(bottom = 20.dp)
                .align(Alignment.BottomCenter)
        )
    }
}