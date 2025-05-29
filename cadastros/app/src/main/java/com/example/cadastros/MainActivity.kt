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
    val navController = rememberNavController() // criação da variavel que guarda o controle da navegação entre as telas
    val viewModel = remember { ProdutoViewModel() } // variavel que é um viewModel para armazenar os dados dos produtos

    // componente que cuida das rotas das telas
    NavHost(
        navController = navController,
        startDestination = MarketScreen.Home.toString() // tela inical da aplicação
    ){
        // criação de composable route para ir passando para o navController a rota atual da aplicação
        composable(MarketScreen.Home.toString()){
            HomeScreen(navController)
        }
        // nas rotas abaixo é passado o viewModel para guardar os dados fornecidos durante a navegação entre as telas
        composable(MarketScreen.CadastroProduto.toString()) {
            CadastroProdutoScreen(navController, viewModel)
        }

        composable(MarketScreen.InfoProduto.toString()) {
            InfoProdutoScreen(navController, viewModel)
        }
    }
}