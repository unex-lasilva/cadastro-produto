package com.example.cadastros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cadastros.MarketScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MarketScreen.Home.toString()
    ) {
        composable(MarketScreen.Home.toString()) {
            HomeScreen(navController)
        }
        composable(MarketScreen.CadastroProduto.toString()) {
            CadastroProdutoScreen(navController)
        }
        composable("infoProduto/{nome}/{quantidade}/{precoCusto}/{precoVenda}/{marca}") { backStackEntry ->
            val nome = backStackEntry.arguments?.getString("nome") ?: ""
            val quantidade = backStackEntry.arguments?.getString("quantidade") ?: ""
            val precoCusto = backStackEntry.arguments?.getString("precoCusto") ?: ""
            val precoVenda = backStackEntry.arguments?.getString("precoVenda") ?: ""
            val marca = backStackEntry.arguments?.getString("marca") ?: ""

            InfoProdutoScreen(
                nome = nome,
                quantidade = quantidade,
                precoCusto = precoCusto,
                precoVenda = precoVenda,
                marca = marca
            )
        }
    }
}