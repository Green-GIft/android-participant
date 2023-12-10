package com.greengift.participant.presentation.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.greengift.participant.ui.theme.main_green

@Composable
fun GreenIndicator() {
    val circleColors: List<Color> = listOf(main_green, Color.White)
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotateAnimation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        ),
        label = ""
    )
    Dialog(onDismissRequest = {}) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(70.dp)
                .rotate(degrees = rotateAnimation)
                .border(
                    width = 10.dp,
                    brush = Brush.sweepGradient(circleColors),
                    shape = CircleShape
                ),
            progress = 1f,
            strokeWidth = 0.dp,
        )
    }
}
