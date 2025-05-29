package com.example.cadastros.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cadastros.classes.MarketScreen
import com.example.cadastros.classes.Produto
import com.example.cadastros.models.ProdutoViewModel
import com.example.cadastros.objects.Defaults.Background

@Composable
fun ProductsScreen(navController: NavController, produtoViewModel: ProdutoViewModel) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Background()
        InnerContent(navController = navController, produtoViewModel = produtoViewModel)
    }
}

@Composable
private fun InnerContent(navController: NavController, produtoViewModel: ProdutoViewModel) {
    val produtos = produtoViewModel.verTodos()
    Column {
        Spacer(modifier = Modifier.height(14.dp))
        if (produtos.isEmpty()) EmptyProductsContent() else ProductsContent(produtos)
        Spacer(modifier = Modifier.height(14.dp))
        Navigation(navController = navController)
    } 
}

@Composable
private fun EmptyProductsContent() {
    Text(
        text = "Não há produtos cadastrados!",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        color = Color.White,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ProductsContent(produtos: Map<Int, Produto>) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(produtos.size) { index ->
            val id = produtos.keys.elementAt(index)
            val produto = produtos.values.elementAt(index)
            ProductCard(idProduto = id, produto = produto)
        }
    }
}

@Composable
private fun ProductCard(idProduto: Int, produto: Produto) {
    Card (
        shape = RoundedCornerShape(7.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9)),
        modifier = Modifier.height(115.dp).fillMaxWidth()
    ) {
        
        val precoCusto = produto.precoCusto?.let { it.setScale(2).toString().replace('.', ',') }
        val precoVenda = produto.precoVenda.setScale(2).toString().replace('.', ',')
        
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) { 
            Column (
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(20.dp).fillMaxHeight(),
            ) { 
                
                Row (verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "#$idProduto", fontSize = 18.sp, color = Color(0xFF085900))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = produto.nome, fontSize = 22.sp, fontWeight = FontWeight.Medium, color = Color.Black)
                    Spacer(modifier = Modifier.width(4.dp))
                    produto.marca?.let { Text(text = "($it)", fontSize = 12.sp, color =  Color.DarkGray) }
                }

                Spacer(modifier = Modifier.height(12.dp))
                
                precoCusto?.let {
                    Text(text = "Custo: R$ $precoCusto", color = Color.Gray)
                    Spacer(modifier = Modifier.height(5.dp))
                }

                Text(text = "Venda: R$ $precoVenda", color = Color.Gray)
                
            }
            
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .background(Color(0xFFFD3B00))
                    .padding(15.dp)
                    .fillMaxHeight()
            ) {
                Text(text = produto.quantidade.toString(), fontSize = 42.sp, color = Color.White, fontWeight = FontWeight.Medium)
            }
        }
        
    }
}

@Composable
private fun Navigation(navController: NavController) {
    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        NavButton(buttonLabel = "Cadastrar", destination = MarketScreen.Register.toString(), navController = navController)
        Spacer(modifier = Modifier.width(10.dp))
        NavButton(buttonLabel = "Voltar", destination = MarketScreen.Home.toString(), navController = navController)
    }
}

@Composable
private fun NavButton(buttonLabel: String, destination: String, navController: NavController) {
    Button(
        onClick = { navController.navigate(destination) },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.width(165.dp).height(55.dp),
        border = BorderStroke(width = 1.2.dp, color = Color.Black),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        content = { Text(text = buttonLabel, color = Color.Black, fontSize = 15.sp) }
    )
}

@Preview
@Composable
private fun ProductsScreenPreview() {
    ProductsScreen(rememberNavController(), viewModel())
}

@Preview
@Composable
private fun ProductCardPreview() {
    ProductCard(1, Produto("laranja", "10", "1.10","1,50", "organics"))
}