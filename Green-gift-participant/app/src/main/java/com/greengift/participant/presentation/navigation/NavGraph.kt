package com.greengift.participant.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.greengift.participant.presentation.view.festival.FestivalScreen
import com.greengift.participant.presentation.view.gift.GiftScreen
import com.greengift.participant.presentation.view.login.LoginScreen
import com.greengift.participant.presentation.view.my_gift.MyGiftScreen
import com.greengift.participant.presentation.view.signup.SignupScreen
import com.greengift.participant.presentation.view.splash.SplashScreen

fun NavGraphBuilder.greenGraph(
    navController: NavController
){
    composable (route = Screen.GiftScreen.route){
        GiftScreen(navController)
    }
    composable (route = Screen.FestivalScreen.route){
        FestivalScreen(navController)
    }
    composable(route = Screen.GiftScreen.route){
        GiftScreen(navController)
    }
    composable(route = Screen.MyGiftScreen.route){
        MyGiftScreen(navController)
    }
    composable(route = Screen.LoginScreen.route){
        LoginScreen(navController)
    }
    composable(route = Screen.SignupScreen.route){
        SignupScreen(navController)
    }
    composable(route = Screen.SplashScreen.route){
        SplashScreen(navController)
    }
    composable(
        route = Screen.FestivalResultScreen.route+ "?festivalId={festivalId}",
        arguments = listOf(
            navArgument("festivalId"){
                type = NavType.LongType
                defaultValue = -1L
            },
        )
    ){
        FestivalScreen(navController)
    }

}