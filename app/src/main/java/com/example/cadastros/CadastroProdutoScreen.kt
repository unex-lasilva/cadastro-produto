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
import com.example.cadastros.data.Produto
import com.example.cadastros.model.ProdutoViewModel
import kotlinx.coroutines.launch

@Composable
fun CadastroProdutoScreen(viewModel: ProdutoViewModel) {


    var nomeProduto by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var precoCusto by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }

    var isNomeValid by remember { mutableStateOf(true) }
    var isQuantidadeValid by remember { mutableStateOf(true) }
    var isPrecoCustoValid by remember { mutableStateOf(true) }
    var isPrecoVendaValid by remember { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
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
                    isNomeValid = it.length > 3
                },
                label = { Text("Nome do produto") },
                isError = !isNomeValid,
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    if (!isNomeValid) {
                        Text(
                            text = "O nome deve ter mais de 3 caracteres",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = quantidade,
                onValueChange = {
                    quantidade = it
                    isQuantidadeValid = it.toIntOrNull() != null && it.toInt() > 0
                },
                label = { Text("Quantidade em estoque") },
                isError = !isQuantidadeValid,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    if (!isQuantidadeValid) {
                        Text(
                            text = "A quantidade deve ser maior que 0",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = precoCusto,
                onValueChange = {
                    precoCusto = it
                    isPrecoCustoValid = it.toDoubleOrNull() != null && it.toDouble() > 0
                },
                label = { Text("Preço de custo") },
                isError = !isPrecoCustoValid,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    if (!isPrecoCustoValid) {
                        Text(
                            text = "O preço deve ser maior que 0",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = precoVenda,
                onValueChange = {
                    precoVenda = it
                    isPrecoVendaValid = it.toDoubleOrNull()?.let { venda -> venda > 0 } ?: false
                },
                label = { Text("Preço de venda") },
                isError = !isPrecoVendaValid,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                supportingText = {
                    if (!isPrecoVendaValid) {
                        Text(
                            text = "O preço deve ser maior que 0",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = marca,
                onValueChange = { marca = it },
                label = { Text("Nome da marca (opcional)") },
                keyboardOptions = KeyboardOptions.Default,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))


            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {

                        isNomeValid = nomeProduto.length > 3
                        isQuantidadeValid = quantidade.toIntOrNull()?.let { it > 0 } ?: false
                        isPrecoCustoValid = precoCusto.toDoubleOrNull()?.let { it > 0 } ?: false
                        isPrecoVendaValid = precoVenda.toDoubleOrNull()?.let { it > 0 } ?: false

                        val isFormValid = isNomeValid && isQuantidadeValid && isPrecoCustoValid && isPrecoVendaValid

                        if (isFormValid) {
                            viewModel.salvarProduto(
                                Produto(
                                    nomeProduto = nomeProduto,
                                    quantidade = quantidade.toInt(),
                                    precoCusto = precoCusto.toDouble(),
                                    precoVenda = precoVenda.toDouble(),
                                    marca = marca.ifBlank { null }
                                )
                            )

                            scope.launch {
                                snackbarHostState.showSnackbar("Formulário salvo com sucesso!")
                            }

                            nomeProduto = ""
                            quantidade = ""
                            precoCusto = ""
                            precoVenda = ""
                            marca = ""
                        }
                    },
                    enabled = true
                ) {
                    Text("Salvar")
                }

                OutlinedButton(onClick = {

                    nomeProduto = ""
                    quantidade = ""
                    precoCusto = ""
                    precoVenda = ""
                    marca = ""
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
    MaterialTheme {
        CadastroProdutoScreen(viewModel = ProdutoViewModel())
    }
}
