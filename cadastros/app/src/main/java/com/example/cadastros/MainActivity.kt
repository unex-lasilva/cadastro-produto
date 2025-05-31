package com.example.cadastros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }

    @Composable
    fun AppNavigation(){
        val navController = rememberNavController()
        val produtos = mutableListOf<Produto>()
        NavHost(navController = navController, startDestination = MarketScreen.Home.toString()){
            composable(MarketScreen.Home.toString()){
                HomeScreen(navController = navController)
            }
            composable(MarketScreen.Cadastro.toString()){
                CadastroProdutoScreen(navController = navController, produtos)
            }
            composable(MarketScreen.Registro.toString()){
                RegistroScreen(navController = navController, produtos)
            }
        }
    }
}
