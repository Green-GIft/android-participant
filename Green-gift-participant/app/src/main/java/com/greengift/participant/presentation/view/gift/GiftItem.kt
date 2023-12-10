package com.greengift.participant.presentation.view.gift

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.greengift.participant.data.util.ImageConverter
import com.greengift.participant.ui.theme.gray3
import com.greengift.participant.ui.theme.main_green
import com.greengift.participant.ui.theme.main_pink
import com.greengift.participant.ui.theme.main_yellow
import com.greengift.participant.ui.theme.typography

@Composable
fun GiftItem(
    modifier: Modifier = Modifier,
    image: String = "",
    name: String = "",
    company: String = "",
    category: String = "",
){
    val color = when(category){
        "마일리지" -> main_pink
        "축제" -> main_yellow
        else -> main_green
    }
    Row(
        modifier = modifier
    ){
        Image(
            bitmap = ImageConverter().getImageBitmap(image),
            contentDescription = "gift_image",
            modifier = Modifier
                .size(96.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(start = 19.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ){
            Text(
                text = name,
                style = typography.displayLarge,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
            Text(
                text = company,
                style = typography.labelLarge,
                color = gray3,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Box(
                modifier = Modifier.fillMaxSize(),
            ){
                Text(
                    text = category,
                    style = typography.labelLarge,
                    modifier = Modifier
                        .background(color, RoundedCornerShape(20.dp))
                        .padding(vertical = 3.dp, horizontal = 8.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .align(Alignment.BottomEnd)
                )
            }
        }
    }
}
