package com.greengift.participant.presentation.view.gift

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.greengift.participant.R
import com.greengift.participant.presentation.component.GreenDivider
import com.greengift.participant.presentation.component.GreenTitle

@Composable
fun GiftScreen(
    navController: NavController,
    viewModel: GiftViewModel = hiltViewModel()
){
    val context = LocalContext.current
    if (viewModel.state.value.error.isNotBlank()){
        Image(
            painter = painterResource(id = R.drawable.wrong_page),
            contentDescription = "wrong page",
            modifier = Modifier.fillMaxSize()
        )
        Toast.makeText(context, "선물 목록을 불러오는데 문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
    }
    GreenTitle(
        title = "My Gift"
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(17.dp),
        ) {
            items(viewModel.state.value.giftList) { gift ->
                GiftItem(
                    image = gift.image,
                    name = gift.name,
                    company = gift.company,
                    category = gift.category
                )
                GreenDivider(modifier = Modifier.padding(top = 17.dp))
            }
        }
    }
}