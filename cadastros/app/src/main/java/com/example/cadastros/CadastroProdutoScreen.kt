package com.example.cadastros


import android.R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import java.math.BigDecimal

@Composable
fun CadastroProdutoScreen() {

    var nomeProduto by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var precoCusto by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }

    var erroNome  by remember { mutableStateOf<String?>(null) }
    var erroQtd by remember { mutableStateOf<String?>(null) }
    var erroPrecoCusto by remember { mutableStateOf<String?>(null) }
    var erroPrecoVenda by remember { mutableStateOf<String?>(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
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
                erroNome = null},
            label = { Text("Nome do produto") },
            isError = erroNome != null,
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth()
        )
        if (erroNome != null){
            Text(erroNome!!, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quantidade em estoque
        OutlinedTextField(
            value = quantidade,
            onValueChange = {
                quantidade = it
                erroQtd = null },
            label = { Text("Quantidade em estoque") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = erroQtd != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (erroQtd != null){
            Text(erroQtd!!, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Preço de custo
        OutlinedTextField(
            value = precoCusto,
            onValueChange = {
                precoCusto = it
                erroPrecoCusto = null},
            label = { Text("Preço de custo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = erroPrecoCusto != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (erroPrecoCusto != null){
            Text(erroPrecoCusto!!, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Preço de venda
        OutlinedTextField(
            value = precoVenda,
            onValueChange = {
                precoVenda = it
                erroPrecoVenda = null},
            label = { Text("Preço de venda") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = erroPrecoVenda != null,
            modifier = Modifier.fillMaxWidth()
        )
        if (erroPrecoVenda != null ){
            Text(erroPrecoVenda!!, color = Color.Red, fontSize = 12.sp)
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
                var isValid = true

                // Verificações

                if (nomeProduto.length < 3){
                    erroNome = "O nome do produto deve ter pelo menos 3 caracteres."
                    isValid = false
                }

                val qtd = quantidade.toIntOrNull()
                if (qtd == null || qtd <= 0){
                    erroQtd = "Quantidade deve ser um número maior que 0."
                    isValid = false
                }

                if (precoCusto.isNotEmpty()){
                    val custo = precoCusto.toBigDecimalOrNull()
                    if (custo == null || custo <= BigDecimal.ZERO){
                        erroPrecoCusto = "Preço de custo deve ser maior que 0."
                        isValid = false
                    }
                }

                val venda = precoVenda.toBigDecimalOrNull()
                if (venda == null || venda <= BigDecimal.ZERO){
                    erroPrecoVenda = "Preço de venda deve ser maior que 0."
                    isValid = false
                }

                if (isValid) {
                    val produto = Produto(
                        nome = nomeProduto,
                        qtd = quantidade.toInt(),
                        precoProduto = precoCusto.toBigDecimalOrNull() ?: BigDecimal.ZERO,
                        precoVenda = precoVenda.toBigDecimal(),
                        marca = marca
                    )

                    val mensagemSnackbar = "Produto: '${produto.nome}' Salvo com Sucesso! Quantidade: ${produto.qtd}, Preço de Venda: R$ ${produto.precoVenda}"

                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(mensagemSnackbar)
                    }
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

                erroNome = null
                erroQtd = null
                erroPrecoCusto = null
                erroPrecoVenda = null
            }) {
                Text("Cancelar")
            }
        }
    }
}
}

@Preview(showBackground = true)
@Composable
fun CadastroProdutoPreview() {
    CadastroProdutoScreen()
}
