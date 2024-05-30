package com.petros.efthymiou.dailypulse.android

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.petros.efthymiou.dailypulse.articles.ArticleViewModel

@Composable
fun AppNavigation(viewModel: ArticleViewModel){
    val navController = rememberNavController()
    AppNavHost(viewModel = viewModel, navController = navController)
}

@Composable
fun AppNavHost(
    viewModel: ArticleViewModel,
    navController: NavHostController
){
    NavHost(navController = navController, startDestination = Screens.ARTICLES_SCREEN.route) {
        composable(
            route = Screens.ARTICLES_SCREEN.route
        ){
            ArticlesScreen(articleViewModel = viewModel) {
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