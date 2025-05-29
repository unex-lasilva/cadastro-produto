package com.example.cadastros.objects

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cadastros.classes.MarketScreen
import com.example.cadastros.models.ProdutoViewModel
import com.example.cadastros.R
import com.example.cadastros.screens.RegisterScreen
import com.example.cadastros.screens.HomeScreen
import com.example.cadastros.screens.ProductsScreen

object Defaults {

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        val produtoViewModel: ProdutoViewModel = viewModel()
        NavHost(navController = navController, startDestination = MarketScreen.Home.toString()) {
            composable(MarketScreen.Home.toString()) {
                HomeScreen(navController = navController)
            }
            composable(MarketScreen.Register.toString()) {
                RegisterScreen(navController = navController, produtoViewModel = produtoViewModel)
            }
            composable(MarketScreen.Products.toString()) {
                ProductsScreen(navController = navController, produtoViewModel = produtoViewModel)
            }
        }
    }

    @Composable
    fun Background(opacity: Float = 0.07f, overlayColor: Color = Color(0xff175CDE), modifier: Modifier =  Modifier) {
        Box(modifier = Modifier.background(overlayColor)) {
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alpha = opacity,
                modifier = modifier.fillMaxSize()
            )
        }
    }
    
}