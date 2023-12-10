package com.greengift.participant.presentation.view.festival

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.greengift.participant.R
import com.greengift.participant.data.dto.FestivalAllDTO
import com.greengift.participant.data.util.ImageConverter
import com.greengift.participant.presentation.component.GiftNavigator
import com.greengift.participant.presentation.component.GreenTitle
import com.greengift.participant.presentation.navigation.Screen
import com.greengift.participant.ui.theme.gray2
import com.greengift.participant.ui.theme.gray4
import com.greengift.participant.ui.theme.main_green
import com.greengift.participant.ui.theme.main_pink
import com.greengift.participant.ui.theme.main_yellow
import com.greengift.participant.ui.theme.typography

@Composable
fun FestivalScreen(
    navController: NavController,
    viewModel: FestivalViewModel = hiltViewModel()
){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        GreenTitle(
            title = "Green Gift"
        ){
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    GiftNavigator(
                        grade = viewModel.grade.value,
                        point = viewModel.point.value,
                        onGiftNavigate = { navController.navigate(Screen.GiftScreen.route) },
                        onProductNavigate = { navController.navigate(Screen.ProductScreen.route) }
                    )
                }
                items(viewModel.state.value.festivalAllDTO){ festival ->
                    FestivalElement(
                        festival = festival,
                        onResult = { navController.navigate(Screen.FestivalResultScreen.route + "?festivalId=${festival.festivalId}") }
                    )
                }
                item { Spacer(modifier = Modifier.height(80.dp)) }
            }
        }
        Image(
            painter = painterResource(id = R.drawable.add),
            contentDescription = "add_button",
            modifier = Modifier
                .padding(end = 20.dp, bottom = 30.dp)
                .size(50.dp)
                .clickable { navController.navigate(Screen.FestivalJoinScreen.route) }
                .align(Alignment.BottomEnd)
        )
    }


}

@Composable
fun FestivalElement(
    festival: FestivalAllDTO,
    onResult: () -> Unit
) {
    val color = when(festival.state){
        "추첨 실패" -> main_pink
        "추첨 성공" -> main_yellow
        "회수 대기" -> gray2
        "회수 완료" -> main_green
        else -> gray2
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(BorderStroke(0.5.dp, main_green), RoundedCornerShape(20.dp))
            .clickable(onClick = onResult)
    ){
        Image(
            bitmap = ImageConverter().getImageBitmap(festival.image),
            contentDescription = "festival_image",
            modifier = Modifier
                .height(120.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(20.dp, 0.dp, 0.dp, 20.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(top = 15.dp, start = 118.dp, end = 20.dp, bottom = 10.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Text(
                text = "${festival.startDate}~${festival.endDate}",
                style = typography.labelLarge,
                color = gray4,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = festival.name,
                style = typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
        }
        Text(
            text = festival.state,
            style = typography.labelLarge,
            modifier = Modifier
                .padding(end = 20.dp, bottom = 10.dp)
                .background(color, RoundedCornerShape(20.dp))
                .padding(vertical = 3.dp, horizontal = 8.dp)
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.BottomEnd)
        )
    }
}
