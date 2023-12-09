package com.greengift.participant.presentation.navigation

sealed class Screen (val route: String){
    object LoginScreen: Screen("login_screen")
    object SplashScreen: Screen("splash_screen")
    object SignupScreen: Screen("sign_up_screen")
    object FestivalScreen: Screen("festival_screen")
    object FestivalResultScreen: Screen("festival_result_screen")
    object GiftScreen: Screen("gift_screen")
    object MyGiftScreen: Screen("my_gift_screen")
    object FestivalJoinScreen: Screen("festival_join_screen")
}