package com.example.cadastros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = MarketScreen.Home.toString()
            ) {
                composable(MarketScreen.Home.toString()) {
                    HomeScreen(navController = navController)
                }
                composable(MarketScreen.CadastroProduto.toString()) {
                    CadastroProdutoScreen(navController = navController)
                }
                composable(MarketScreen.InfoProduto.toString()+"/{produtoNome}/{produtoQuantidade}/{produtoPrecoCusto}/{produtoPrecoVenda}/{produtoMarca}") {  backStackEntry ->
                    val produtoNome = backStackEntry.arguments?.getString("produtoNome")
                    val produtoQuantidade = backStackEntry.arguments?.getString("produtoQuantidade")?.toIntOrNull() ?: 0
                    val produtoPrecoCusto = backStackEntry.arguments?.getString("produtoPrecoCusto")?.toDoubleOrNull() ?: 0.0
                    val produtoPrecoVenda = backStackEntry.arguments?.getString("produtoPrecoVenda")?.toDoubleOrNull() ?: 0.0
                    val produtoMarca = backStackEntry.arguments?.getString("produtoMarca")


                    val produto = Produto(
                        nome = produtoNome ?: "",
                        quantidade = produtoQuantidade,
                        precoCusto = produtoPrecoCusto,
                        precoVenda = produtoPrecoVenda,
                        marca = produtoMarca ?: ""
                    )

                    InfoProdutoScreen(navController = navController, produto = produto)
                }
            }


        }
    }
}
