package com.greengift.participant.presentation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun GreenTitleWithButton(
    modifier: Modifier = Modifier,
    title: String = "제목",
    buttonText: String,
    onButtonClick: () -> Unit = {},
    content: @Composable (ColumnScope.() -> Unit),
){
    Box(
        modifier = modifier
            .padding(30.dp)
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier.padding(bottom = 50.dp)
        ){
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth()
            )
            GreenDivider(modifier = Modifier.padding(top = 7.dp, bottom = 10.dp))
            Column(content=content)
        }
        GreenButton(
            text = buttonText,
            modifier = Modifier.align(Alignment.BottomCenter),
            onClick = onButtonClick
        )
    }
}