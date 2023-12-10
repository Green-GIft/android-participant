package com.greengift.participant.presentation.view.festival_result

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.greengift.participant.R
import com.greengift.participant.data.dto.FestivalResultDTO
import com.greengift.participant.presentation.component.GreenButton
import com.greengift.participant.data.util.ImageConverter
import com.greengift.participant.presentation.component.GreenIndicator
import com.greengift.participant.presentation.navigation.Screen
import com.greengift.participant.ui.theme.main_green
import com.greengift.participant.ui.theme.typography

@Composable
fun FestivalResultScreen(
    navController: NavController,
    viewModel: FestivalResultViewModel = hiltViewModel()
){
    val context = LocalContext.current
    val value = viewModel.state.value
    val text = if (value.isEmpty) "축하드려요!\n선물에 당첨되셨어요" else "아쉽지만 당첨이 되지 않았어요\n대신 마일리지 10P를 드릴게요!"
    val buttonText = if (value.isEmpty) "선물함으로 이동하기" else "메인으로"
    val buttonClick = if (value.isEmpty) Screen.GiftScreen.route else Screen.FestivalScreen.route

    if (value.isLoading){ GreenIndicator() }
    else if (value.error.isBlank()){
        Box(
            modifier = Modifier.padding(vertical = 50.dp, horizontal = 40.dp)
        ){
            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 60.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = text,
                    style = typography.titleLarge,
                    color = main_green,
                    textAlign = TextAlign.Center
                )
                if (value.isEmpty) GiftResult(value.festivalResult ?: FestivalResultDTO())
                else MileageResult(Modifier.fillMaxSize())
            }
            GreenButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = buttonText,
                onClick = {navController.navigate(buttonClick)}
            )
        }
    }
    else {
        Image(
            painter = painterResource(id = R.drawable.not_finished_festival),
            contentDescription = "wrong page",
            modifier = Modifier.fillMaxSize()
        )
        Toast.makeText(context, value.error, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun MileageResult(modifier: Modifier = Modifier) {
    Image(
        painterResource(id = R.drawable.gift_result),
        contentDescription = "mileage- result",
        modifier = modifier
    )
}

@Composable
fun GiftResult(
    festivalResult: FestivalResultDTO = FestivalResultDTO()
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .border(BorderStroke(1.dp, main_green), RoundedCornerShape(20.dp))
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            bitmap = ImageConverter().getImageBitmap(festivalResult.image),
            contentDescription = "gift_image",
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Text(
            text = festivalResult.name,
            style = typography.titleMedium,
            modifier = Modifier.padding(top = 40.dp, bottom = 25.dp),
        )
        Text(
            text = festivalResult.dueDate,
            style = typography.bodyMedium,
            color = Color(0xFF7E7E7E)
        )
    }
}
