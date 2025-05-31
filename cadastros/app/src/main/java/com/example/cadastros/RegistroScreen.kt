package com.example.cadastros

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun RegistroScreen(navController: NavController, produtos:MutableList<Produto>){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(produtos.isEmpty()){
            Text(
                text = "Nenhum produto cadastrado"
            )
        }else{
            produtos.forEach{
                ListaInformacoes(it)
            }
        }


        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {navController.navigate(MarketScreen.Home.toString())}
        ) {
            Text("Voltar")
        }
    }

}

@Composable
fun ListaInformacoes(produto: Produto){
    Column {
        Text(text = "Nome: ${produto.nome}" )
        Text(text = "Quantidade: ${produto.quantidade.toString()}")
        Text(text = "Custo: ${produto.custo.toString()}")
        Text(text = "Preço de Venda: ${produto.preco.toString()}")
        Text(text = "Marca: ${produto.marca}")
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Preview
@Composable
fun RegistroScreenPreview(){
    RegistroScreen(rememberNavController(), produtos = mutableListOf())
}