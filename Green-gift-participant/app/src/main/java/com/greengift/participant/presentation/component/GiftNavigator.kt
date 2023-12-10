package com.greengift.participant.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.greengift.participant.R
import com.greengift.participant.ui.theme.typography

@Composable
fun GiftNavigator(
    grade: String,
    point: Long,
    onGiftNavigate: () -> Unit = {},
    onProductNavigate: () -> Unit = {}
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
                .clickable(onClick = onProductNavigate)
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