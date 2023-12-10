package com.greengift.participant.presentation.view.product

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.greengift.participant.R
import com.greengift.participant.presentation.component.GiftNavigator
import com.greengift.participant.presentation.component.GreenDivider
import com.greengift.participant.presentation.component.GreenTitle
import com.greengift.participant.presentation.event.GreenGiftEvent
import com.greengift.participant.presentation.navigation.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductScreen(
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
){
    val context = LocalContext.current
    BackHandler { navController.navigate(Screen.FestivalScreen.route) }
    LaunchedEffect(key1 = true){
        viewModel.eventflow.collectLatest { event ->
            when (event){
                GreenGiftEvent.BUY -> {
                    Toast.makeText(context, "성공적으로 구매완료했습니다.", Toast.LENGTH_SHORT).show()
                }
                is GreenGiftEvent.ERROR -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
                is GreenGiftEvent.LOADING -> {}
                else -> {}
            }
        }
    }

    if (viewModel.state.value.error.isNotBlank()){
        Image(
            painter = painterResource(id = R.drawable.wrong_page),
            contentDescription = "wrong page",
            modifier = Modifier.fillMaxSize()
        )
        Toast.makeText(context, "선물 목록을 불러오는데 문제가 발생했습니다.", Toast.LENGTH_SHORT).show()
    }
    GreenTitle(
        title = "Buy Product"
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(17.dp),
        ) {
            item {
                GiftNavigator(
                    grade = viewModel.grade.value,
                    point = viewModel.point.longValue,
                    onGiftNavigate = { navController.navigate(Screen.GiftScreen.route) },
                )
            }
            items(viewModel.state.value.productList) { gift ->
                ProductItem(
                    image = gift.image,
                    name = gift.name,
                    company = gift.company,
                    price = gift.price,
                    onBuyClick = {viewModel.buyProduct(gift.productId, gift.price)}
                )
                GreenDivider(modifier = Modifier.padding(top = 17.dp))
            }
        }
    }
}