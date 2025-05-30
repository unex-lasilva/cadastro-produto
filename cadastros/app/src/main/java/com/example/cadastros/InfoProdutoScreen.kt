package com.example.cadastros

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun InfoProdutoScreen(
    nome: String,
    quantidade: String,
    precoCusto: String,
    precoVenda: String,
    marca: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Nome: $nome")
        Text("Quantidade: $quantidade")
        Text("Preço de Custo: $precoCusto")
        Text("Preço de Venda: $precoVenda")
        Text("Marca: $marca")
    }
}