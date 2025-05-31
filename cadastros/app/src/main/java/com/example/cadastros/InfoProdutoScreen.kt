package com.example.cadastros

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun InfoProdutoScreen(navController: NavController, produto: Produto) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Nome:")
        Text(produto.nome)

        Spacer(modifier = Modifier.height(10.dp))

        Text("Quantidade:")
        Text(produto.quantidade.toString())

        Spacer(modifier = Modifier.height(10.dp))

        Text("Preco Venda:")
        Text(produto.precoVenda.toString())

        Spacer(modifier = Modifier.height(10.dp))

        Text("Preço Custo:")
        Text(produto.precoCusto.toString())

        Spacer(modifier = Modifier.height(10.dp))


        Text("Marca:")
        Text(produto.marca)
    }
}