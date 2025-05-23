package com.example.cadastros

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

class Produto(
    val nome: String,
    val qtd: Int,
    val precoCusto: Double,
    val precoVenda: Double,
    val marca: String
)

@Composable
fun CadastroProdutoScreen() {

    var nomeProduto by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var precoCusto by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }

    // Flags de erro
    var nomeInvalido by remember { mutableStateOf(false) }
    var quantidadeInvalida by remember { mutableStateOf(false) }
    var precoCustoInvalido by remember { mutableStateOf(false) }
    var precoVendaInvalido by remember { mutableStateOf(false) }

    fun limparCampos(){
        nomeProduto = ""
        quantidade = ""
        precoCusto = ""
        precoVenda = ""
        marca = ""
        nomeInvalido = false
        quantidadeInvalida = false
        precoCustoInvalido = false
        precoVendaInvalido = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Cadastro de produto",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 16.dp, start = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nome do produto
        OutlinedTextField(
            value = nomeProduto,
            onValueChange = {
                nomeProduto = it
                nomeInvalido = false
            },
            label = { Text("Nome do produto") },
            keyboardOptions = KeyboardOptions.Default,
            isError = nomeInvalido,
            modifier = Modifier.fillMaxWidth()
        )
        if (nomeInvalido) {
            Text("Nome precisa ter pelo menos 4 letras.", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quantidade em estoque
        OutlinedTextField(
            value = quantidade,
            onValueChange = {
                quantidade = it
                quantidadeInvalida = false
            },
            label = { Text("Quantidade em estoque") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = quantidadeInvalida,
            modifier = Modifier.fillMaxWidth()
        )
        if (quantidadeInvalida) {
            Text("Informe uma quantidade válida (> 0).", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Preço de custo
        OutlinedTextField(
            value = precoCusto,
            onValueChange = {
                precoCusto = it
                precoCustoInvalido = false
            },
            label = { Text("Preço de custo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = precoCustoInvalido,
            modifier = Modifier.fillMaxWidth()
        )
        if (precoCustoInvalido) {
            Text("Preço de custo inválido.", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Preço de venda
        OutlinedTextField(
            value = precoVenda,
            onValueChange = {
                precoVenda = it
                precoVendaInvalido = false
            },
            label = { Text("Preço de venda") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = precoVendaInvalido,
            modifier = Modifier.fillMaxWidth()
        )
        if (precoVendaInvalido) {
            Text("Preço de venda inválido.", color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nome da marca
        OutlinedTextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text("Nome da marca") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botões
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                // Validações
                nomeInvalido = nomeProduto.length < 4
                quantidadeInvalida = quantidade.toIntOrNull()?.let { it <= 0 } ?: true
                precoCustoInvalido = precoCusto.isNotEmpty() && precoCusto.toDoubleOrNull()?.let { it <= 0.0 } ?: true
                precoVendaInvalido = precoVenda.toDoubleOrNull()?.let { it <= 0 } ?: true


                if (!nomeInvalido && !quantidadeInvalida && !precoCustoInvalido && !precoVendaInvalido) {
                    // Salvar o produto
                }
            }) {
                Text("Salvar")
            }

            OutlinedButton(onClick = {
                limparCampos()
            }) {
                Text("Cancelar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroProdutoPreview() {
    CadastroProdutoScreen()
}