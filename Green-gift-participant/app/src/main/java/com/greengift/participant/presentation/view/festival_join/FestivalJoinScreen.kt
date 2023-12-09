package com.greengift.participant.presentation.view.festival_join

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.greengift.participant.R
import com.greengift.participant.presentation.component.GreenButton
import com.greengift.participant.presentation.component.GreenTextField
import com.greengift.participant.presentation.component.addFocusCleaner
import com.greengift.participant.presentation.event.GreenGiftEvent
import com.greengift.participant.presentation.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FestivalJoinScreen(
    navController: NavController,
    viewModel: FestivalJoinViewModel = hiltViewModel()
){
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is GreenGiftEvent.JOIN -> {
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
                text = viewModel.festivalName.value.name,
                onValueChange = {viewModel.onEvent(FestivalJoinEvent.EnteredFestivalName(it))},
                placeholder = "축제 이름",
            )
            GreenButton(
                text = "축제 참여하기",
                onClick = {viewModel.onEvent(FestivalJoinEvent.FestivalJoin)},
                modifier = Modifier.padding(top = 30.dp)
            )
        }

    }
}