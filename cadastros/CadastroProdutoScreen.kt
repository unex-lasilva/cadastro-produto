package com.example.cadastros

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.cadastros.model.Produto

@Composable
fun CadastroProdutoScreen() {
    var nomeProduto by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var precoCusto by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }
    var mensagem by remember { mutableStateOf("") }

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
            onValueChange = { nomeProduto = it },
            label = { Text("Nome do produto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade em estoque") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = precoCusto,
            onValueChange = { precoCusto = it },
            label = { Text("Preço de custo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = precoVenda,
            onValueChange = { precoVenda = it },
            label = { Text("Preço de venda") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = marca,
            onValueChange = { marca = it },
            label = { Text("Nome da marca (opcional)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (mensagem.isNotEmpty()) {
            Text(
                text = mensagem,
                color = if ("sucesso" in mensagem.lowercase()) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                if (nomeProduto.length <= 3) {
                    mensagem = "O nome do produto deve ter mais de 3 caracteres."
                    return@Button
                }

                val qtd = quantidade.toIntOrNull()
                if (qtd == null || qtd <= 0) {
                    mensagem = "Quantidade deve ser maior que 0."
                    return@Button
                }

                val precoV = precoVenda.toDoubleOrNull()
                if (precoV == null || precoV <= 0.0) {
                    mensagem = "Preço de venda deve ser maior que 0 e não pode estar vazio."
                    return@Button
                }

                val precoC = precoCusto.toDoubleOrNull() ?: 0.0
                if (precoCusto.isNotEmpty() && precoC <= 0.0) {
                    mensagem = "Preço de custo deve ser maior que 0 ou deixado vazio."
                    return@Button
                }

                val produto = Produto(
                    nome = nomeProduto,
                    quantidade = qtd,
                    precoCusto = precoC,
                    precoVenda = precoV,
                    marca = marca
                )

                mensagem = "Produto salvo com sucesso: $produto"
            }) {
                Text("Salvar")
            }

            OutlinedButton(onClick = {
                nomeProduto = ""
                quantidade = ""
                precoCusto = ""
                precoVenda = ""
                marca = ""
                mensagem = ""
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
