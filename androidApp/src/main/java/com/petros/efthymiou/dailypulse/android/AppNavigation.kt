package com.petros.efthymiou.dailypulse.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    AppNavHost(navController = navController)
}

@Composable
fun AppNavHost(
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = Screens.ARTICLES_SCREEN.route) {
        composable(
            route = Screens.ARTICLES_SCREEN.route
        ){
            ArticlesScreen() {
                navController.navigate(Screens.ABOUT_SCREEN.route)
            }
        }
        composable(
            route = Screens.ABOUT_SCREEN.route
        ){
            AboutScreen{
                navController.popBackStack()
            }
        }
    }
}