package com.example.cadastros.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cadastros.classes.MarketScreen
import com.example.cadastros.classes.ProductInfoInputHandler
import com.example.cadastros.classes.Produto
import com.example.cadastros.models.ProdutoViewModel
import com.example.cadastros.objects.Defaults.Background

@Composable
fun RegisterScreen(navController: NavController, produtoViewModel: ProdutoViewModel) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Background()
        InnerContent(navController = navController, produtoViewModel = produtoViewModel)
    }
}

@Composable
private fun InnerContent(navController: NavController, produtoViewModel: ProdutoViewModel) {
    
    // Variáveis guardando valores de entrada para cada atributo do Produto
    var nomeProduto by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var precoCusto by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }

    // Variáveis para controle de estado e erros dentro da tela de registro
    var onInitialState by remember { mutableStateOf(true) }
    var errors by remember { mutableStateOf(mapOf<String, String>()) }
    
    val infoInputHandler = ProductInfoInputHandler(errorsMap = errors, useInitialState = onInitialState)

    Surface (shape = RoundedCornerShape(10.dp), modifier = Modifier.fillMaxWidth(0.95f)) {
        Column (modifier = Modifier.padding(horizontal = 10.dp, vertical = 20.dp).background(Color.White)) {
            
            // Exibição do Título
            Title("Cadastro de produto")
            Spacer(modifier = Modifier.height(24.dp))

            // Exibição do nome do Produto
            infoInputHandler.NomeProduto(
                value = nomeProduto,
                onValueChange = {
                    nomeProduto = it
                    errors =  Produto.Validador(it, quantidade, precoCusto, precoVenda) // ação deveria ser otimizada
                    onInitialState = false
                } 
            )

            // Exibição da quantidade em estoque
            infoInputHandler.Quantidade(
                value = quantidade,
                onValueChange = {
                    quantidade = it
                    errors = Produto.Validador(nomeProduto, it, precoCusto, precoVenda) // ação deveria ser otimizada
                    onInitialState = false
                },
            )
            
            // Exibição do preco de custo
            infoInputHandler.PrecoCusto (
                value = precoCusto,
                onValueChange = {
                    precoCusto = it
                    errors = Produto.Validador(nomeProduto, quantidade, it, precoVenda) // ação deveria ser otimizada
                    onInitialState = false
                },
            )

            // Exibição do preco de venda
            infoInputHandler.PrecoVenda (
                value = precoVenda,
                onValueChange = {
                    precoVenda = it
                    errors = Produto.Validador(nomeProduto, quantidade, precoCusto, it) // ação deveria ser otimizada
                    onInitialState = false
                },
            )

            // Exibição do nome da marca
            infoInputHandler.Marca (
                value = marca,
                onValueChange = {
                    marca = it
                    errors = Produto.Validador(nomeProduto, quantidade, precoCusto, precoVenda) // ação deveria ser otimizada
                    onInitialState = false
                },
            )
            
            // Exibição dos botões de ação
            Row (horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) { 
                Button (
                    enabled = Produto.Validador.estaOk(nomeProduto, quantidade, precoCusto, precoVenda),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF228300)),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.width(165.dp).height(55.dp),
                    content = { Text("Salvar") },
                    onClick = {
                        produtoViewModel.adicionar(nomeProduto, quantidade, precoCusto, precoVenda, marca)
                        navController.navigate(MarketScreen.Products.toString())
                    }
                )
                OutlinedButton(
                    onClick = { navController.popBackStack() },
                    shape = RoundedCornerShape(5.dp),
                    content = { Text("Cancelar") },
                    modifier = Modifier.width(165.dp).height(55.dp)
                )
            }
        }
    }
}

@Composable
private fun Title(titleText: String, fontSize: Int = 28, modifier: Modifier = Modifier) {
    Text(
        text = titleText,
        textAlign = TextAlign.Center,
        fontSize = fontSize.sp,
        modifier = modifier.padding(top = 16.dp, start = 8.dp).fillMaxWidth()
    )
}

// Pré-Visualizações
@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController(), viewModel())
}