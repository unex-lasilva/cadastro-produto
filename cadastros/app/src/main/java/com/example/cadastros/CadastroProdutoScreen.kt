package com.example.cadastros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview


class produto {
    val nome = String
    val qtEstoque = Int
    val precoCusto = Double
    val precoVenda = Double
    val nomeMarca = String

}

@Composable
fun CadastroProdutoScreen() {

    var nomeProduto by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var precoCusto by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }

    //estado de erro
    var erroNomeProduto by remember { mutableStateOf(false) }
    var erroQuantidade by remember { mutableStateOf(false) }
    var erroPrecoVenda by remember { mutableStateOf(false) }
    var erroPrecoCusto by remember { mutableStateOf(false) }

    fun limparCampos() {
        nomeProduto = ""
        quantidade = ""
        precoCusto = ""
        precoVenda = ""
        marca = ""
        erroNomeProduto = false
        erroQuantidade = false
        erroPrecoCusto = false
        erroPrecoVenda = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Cadastro de produto",
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 16.dp, start = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nomeProduto,
            onValueChange = {
                nomeProduto = it
                erroNomeProduto = it.length < 3
            },
            label = { Text("Nome do produto") },
            isError = erroNomeProduto,
            modifier = Modifier.fillMaxWidth()
        )
        if (erroNomeProduto) {
            Text("O nome deve ter no mínimo 3 caracteres.", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = quantidade,
            onValueChange = {
                quantidade = it
                val qtd = it.toIntOrNull()
                erroQuantidade = qtd == null || qtd <= 0
            },
            label = { Text("Quantidade em estoque") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = erroQuantidade,
            modifier = Modifier.fillMaxWidth()
        )
        if (erroQuantidade) {
            Text("A quantidade deve ser maior que zero.", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = precoCusto,
            onValueChange = {
                precoCusto = it
                erroPrecoCusto = it.isNotBlank() &&
                        (it.toDoubleOrNull()?.let { valor -> valor <= 0.0 } ?: true)
            },
            label = { Text("Preço de custo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = erroPrecoCusto,
            modifier = Modifier.fillMaxWidth()
        )
        if (erroPrecoCusto) {
            Text("Se preenchido, o preço de custo deve ser maior que zero.", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = precoVenda,
            onValueChange = {
                precoVenda = it
                erroPrecoVenda = it.toDoubleOrNull()?.let { valor -> valor <= 0.0 } ?: true
            },
            label = { Text("Preço de venda") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = erroPrecoVenda,
            modifier = Modifier.fillMaxWidth()
        )
        if (erroPrecoVenda) {
            Text("O preço de venda é obrigatório e deve ser maior que zero.", color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text("Nome da marca") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                erroNomeProduto = nomeProduto.length < 3
                erroQuantidade = quantidade.toIntOrNull()?.let { it <= 0 } ?: true
                erroPrecoVenda = precoVenda.toDoubleOrNull()?.let { it <= 0.0 } ?: true
                erroPrecoCusto = precoCusto.isNotBlank() &&
                        precoCusto.toDoubleOrNull()?.let { it <= 0.0 } ?: true

                val tudoValido = !erroNomeProduto && !erroQuantidade && !erroPrecoVenda && !erroPrecoCusto

                if (tudoValido) {
                    println("Produto salvo com sucesso!")

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