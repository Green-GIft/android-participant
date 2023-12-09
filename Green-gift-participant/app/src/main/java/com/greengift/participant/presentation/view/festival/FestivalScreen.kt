package com.greengift.participant.presentation.view.festival

import android.graphics.BitmapFactory
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.greengift.participant.R
import com.greengift.participant.data.dto.FestivalAllDTO
import com.greengift.participant.presentation.component.GreenTitle
import com.greengift.participant.presentation.navigation.Screen
import com.greengift.participant.ui.theme.gray2
import com.greengift.participant.ui.theme.gray4
import com.greengift.participant.ui.theme.main_green
import com.greengift.participant.ui.theme.main_pink
import com.greengift.participant.ui.theme.main_yellow
import com.greengift.participant.ui.theme.typography
import java.util.Base64

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
                    FestivalNavigation(
                        grade = viewModel.state.value.grade,
                        point = viewModel.state.value.point,
                        onGiftNavigate = { navController.navigate(Screen.GiftScreen.route) },
                        onMileageNavigate = { navController.navigate(Screen.MyGiftScreen.route) }
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
fun FestivalNavigation(
    grade: String,
    point: Long,
    onGiftNavigate: () -> Unit = {},
    onMileageNavigate: () -> Unit = {}
) {
    val image = when (grade){
        "씨앗" -> R.drawable.seed
        "새싹" -> R.drawable.plant
        "꽃" -> R.drawable.flower
        "열매" -> R.drawable.fruit
        else -> R.drawable.tree
    }
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .height(95.dp)
    ){
        Row(
            modifier = Modifier
                .padding(end = 110.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFE6F0EC), RoundedCornerShape(20.dp))
                .clickable(onClick = onMileageNavigate)
                .padding(horizontal = 15.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column{
                Text(
                    text = "$grade 등급",
                    style = typography.titleMedium
                )
                Row(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.point),
                        contentDescription = "point image",
                        modifier = Modifier.padding(end = 4.dp).size(15.dp)
                    )
                    Text(
                        text = "$point P",
                        style = typography.bodyMedium
                    )

                }
            }
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp)
                    .width(2.dp)
                    .background(color = Color(0xFFDBDBD9))
            )
            Box(modifier = Modifier.fillMaxSize()){
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "$grade 등급",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.gift_navigate),
            contentDescription = "gift_navigator",
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .size(100.dp)
                .clickable(onClick = onGiftNavigate)
                .align(Alignment.CenterEnd)
        )
    }
}

@Composable
fun FestivalElement(
    festival: FestivalAllDTO,
    onResult: () -> Unit
) {
    val decoded = try {
        Base64.getDecoder().decode(festival.image)
    } catch(e: Exception){
        Base64.getDecoder().decode(MY_IMAGE)
    }
    val bitmap = BitmapFactory.decodeByteArray(decoded, 0, decoded.size)

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
            bitmap = bitmap.asImageBitmap(),
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
