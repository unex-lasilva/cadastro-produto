package com.example.cadastros

import androidx.compose.foundation.layout.*
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement
            .Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {
            navController.navigate(MarketScreen.CadastroProduto.toString())
        }) {
            Text("Cadastrar produto")
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(onClick = {
        }) {
            Text("Cancelar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}