package com.example.cadastros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CadastroProdutoScreen() {

    var nomeProduto by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var precoCusto by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }

    val isNomeValido = nomeProduto.length >= 3
    val isQuantidadeValida = quantidade.toIntOrNull()?.let { it > 0 } == true
    val isPrecoCustoValido = precoCusto.toDoubleOrNull()?.let { it > 0 } == true
    val isPrecoVendaValido = precoVenda.toDoubleOrNull()?.let { it > 0 } == true
    var formSubmited by remember { mutableStateOf(false) }
    var enviou by remember { mutableStateOf(false) }
    var produto by remember { mutableStateOf<Produto?>(null) }


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

            },
            label = { Text("Nome do produto") },
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth()
        )

        if ((nomeProduto.length < 3) && (formSubmited || nomeProduto.isNotEmpty())) {            Text(
                text = "O nome do produto deve ter pelo menos 3 caracteres",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quantidade em estoque
        OutlinedTextField(
            value = quantidade,
            onValueChange = {
                    quantidade = it

            },
            label = { Text("Quantidade em estoque") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        val quantidadeint = quantidade.toIntOrNull();

        if ((quantidadeint == null || quantidadeint < 1) && (formSubmited || quantidade.isNotEmpty())) {
            Text(
                text = "Deve haver pelo menos 1 produto em estoque",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Preço de custo
        OutlinedTextField(
            value = precoCusto,
            onValueChange = {
                    precoCusto = it

            },
            label = { Text("Preço de custo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        val precoCustoDoub = precoCusto.toDoubleOrNull()

        if (precoCusto.isNotEmpty() && (precoCustoDoub == null || precoCustoDoub <= 0)) {
                Text(
                    text = "O produto deve ter um custo maior que zero",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Preço de venda
        OutlinedTextField(
            value = precoVenda,
            onValueChange = { precoVenda = it },
            label = { Text("Preço de venda") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        val precoVendaDoub = precoVenda.toDoubleOrNull()

        if ((precoVendaDoub == null || precoVendaDoub == 0.0) && (formSubmited || precoVenda.isNotEmpty())) {
            Text(
                text = "O preço de venda deve ser maior que zero",
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))


        // Nome da marca
        OutlinedTextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text("Nome da marca") },
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botões
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                formSubmited = true
                if (isNomeValido && isQuantidadeValida && isPrecoCustoValido && isPrecoVendaValido) {
                    enviou = true
                    produto = Produto(nomeProduto, quantidadeint, precoCustoDoub, precoVendaDoub, marca)

                }
            }) {
                Text("Salvar")
            }

            OutlinedButton(onClick = {
                nomeProduto = ""
                quantidade = ""
                precoCusto = ""
                precoVenda = ""
                marca = ""
                formSubmited = false
                enviou = false
            }) {
                Text("Cancelar")
            }
        }

        if (enviou) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Produto salvo com sucesso!",
                    color = Color.Green,
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CadastroProdutoPreview() {
    CadastroProdutoScreen()
}
