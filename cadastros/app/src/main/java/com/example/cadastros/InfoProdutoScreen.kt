package com.example.cadastros

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import java.util.Locale
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*


@Composable
fun InfoProdutoScreen(
    navController: NavController,
    viewModel: ProdutoViewModel
) {
    val listaProdutos by viewModel.produtos.collectAsState()

    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFF5F5F5),
                tonalElevation = 4.dp
            ) {
                Spacer(modifier = Modifier.weight(0.14f))
                // Botão de voltar
                IconButton(onClick = {
                    navController.popBackStack(MarketScreen.Home.toString(), inclusive = false)
                },
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.DarkGray,
                        modifier = Modifier
                            .size(36.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(0.7f))

                // Botão para adicionar produtos
                Button(
                    onClick = {
                        navController.navigate(MarketScreen.CadastroProduto.toString())
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(56.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Adicionar",
                        tint = Color.White,
                    )
                }

                Spacer(modifier = Modifier.weight(1.1f))
            }
        }
    ) { innerPadding ->

        // Coluna para a lista dos produtos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Text(
                    text = "Informações dos Produtos",
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )
            }

            // Se a lista estiver vazia, mostra uma mensagem
            if (listaProdutos.isEmpty()) {
                item {
                    Text(
                        text = "Nenhum produto cadastrado ainda.",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                }
            } else {
                // Senão, exibe os produtos
                items(listaProdutos) { produto ->
                    ProdutoCard(produto, onRemove = { produtoRemover ->
                        viewModel.removerProduto(produtoRemover)
                    })
                }
            }

            item {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}

@Composable
fun ProdutoCard(produto: Produto, onRemove: (Produto) -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            val dados = listOf(
                "ID" to produto.id.toString(),
                "Nome" to produto.nome,
                "Quantidade" to produto.qtd.toString(),
                "Preço do Produto" to "R$%.2f".format(Locale("pt", "BR"), produto.precoProduto),
                "Preço de Venda" to "R$%.2f".format(Locale("pt", "BR"), produto.precoVenda),
                "Marca" to produto.marca
            )

            dados.forEachIndexed { index, (chave, valor) ->
                val isId = chave == "ID"
                Row(
                    modifier = Modifier.padding(bottom = if (index == dados.lastIndex) 0.dp else 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$chave: ",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = if (isId) Color(0xFF009688) else Color.Black,
                            fontWeight = if (isId) FontWeight.Bold else FontWeight.Medium,
                            fontSize = if (isId) 24.sp else 18.sp
                        )
                    )
                    Text(
                        text = valor,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = if (isId) FontWeight.Bold else FontWeight.Normal,
                            fontSize = if (isId) 24.sp else 18.sp,
                            color = if (isId) Color(0xFF4CAF50) else Color.DarkGray
                        )
                    )

                    if (index == dados.lastIndex) {
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(
                            onClick = { onRemove(produto) },
                            modifier = Modifier.size(35.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Remover produto",
                                tint = Color.Red,
                                modifier = Modifier.size(35.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


            @Preview(showBackground = true)
@Composable
fun ViewInfoProduto(){
    InfoProdutoScreen(navController = rememberNavController(), viewModel = FakeProdutoViewModel())
}
