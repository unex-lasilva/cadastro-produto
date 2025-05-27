package com.example.cadastros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val viewModel = remember { ProdutoViewModel() }

    NavHost(
        navController = navController,
        startDestination = MarketScreen.Home.toString()
    ){
        composable(MarketScreen.Home.toString()){
            HomeScreen(navController)
        }

        composable(MarketScreen.CadastroProduto.toString()) {
            CadastroProdutoScreen(navController, viewModel)
        }

        composable(MarketScreen.InfoProduto.toString()) {
            InfoProdutoScreen(navController, viewModel)
        }
    }
}