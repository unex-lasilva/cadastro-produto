package com.example.cadastros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SetupNavGraph()
        }
    }
}

@Composable
fun SetupNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MarketScreen.Home.name
    ) {
        composable(MarketScreen.Home.name) {
            HomeScreen(navController)
        }
        composable(MarketScreen.CadastroProduto.name) {
            CadastroProdutoScreen(navController)
        }
        composable(
            route = "${MarketScreen.InfoProduto.name}/{nome}/{quantidade}/{precoCusto}/{precoVenda}/{marca}"
        ) { backStackEntry ->
            val nome = backStackEntry.arguments?.getString("nome").orEmpty()
            val quantidade = backStackEntry.arguments?.getString("quantidade")?.toIntOrNull() ?: 0
            val precoCusto = backStackEntry.arguments?.getString("precoCusto")?.toDoubleOrNull() ?: 0.0
            val precoVenda = backStackEntry.arguments?.getString("precoVenda")?.toDoubleOrNull() ?: 0.0
            val marca = backStackEntry.arguments?.getString("marca").orEmpty()

            val produto = Produto(
                nome = nome,
                quantidade = quantidade,
                precoCusto = precoCusto,
                precoVenda = precoVenda,
                marca = marca
            )

            InfoProdutoScreen(navController, produto)
        }
    }
}