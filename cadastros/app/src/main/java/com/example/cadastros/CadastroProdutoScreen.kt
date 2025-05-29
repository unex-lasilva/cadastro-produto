package com.example.cadastros

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController

 class Produto(
    var nome: String,
    var quantidade: Int?,
    var precoCusto: Double?,
    var precoVenda: Double?,
    var marca: String
)

@Composable
fun CadastroProdutoScreen(navController: NavController) {
    var nomeProduto by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var precoCusto by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }

    var validator by remember { mutableStateOf(false) }
    var message  by remember { mutableStateOf("") }

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
            onValueChange = { nomeProduto = it },
            label = { Text("Nome do produto") },
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth(),
            isError = nomeProdutoValidator(nomeProduto, validator) != null,
            supportingText = {
                val errors = nomeProdutoValidator(nomeProduto, validator)
                if (errors != null) {
                    Text(errors);
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Quantidade em estoque
        OutlinedTextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade em estoque") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            isError = quantidadeProdutoValidator(quantidade, validator) != null,
            supportingText = {
                val errors = quantidadeProdutoValidator(quantidade, validator);
                if (errors != null) {
                    Text(errors)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Preço de custo
        OutlinedTextField(
            value = precoCusto,
            onValueChange = { precoCusto =  it },
            label = { Text("Preço de custo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            isError = precoCustoProdutoValidator(precoCusto, validator) != null,
            supportingText = {
                val erros = precoCustoProdutoValidator(precoCusto, validator);
                if (erros != null) {
                    Text(erros)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Preço de venda
        OutlinedTextField(
            value = precoVenda,
            onValueChange = { precoVenda = it },
            label = { Text("Preço de venda") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            isError = precoVendaProdutoValidator(precoVenda, validator) != null,
            supportingText = {
                val erros = precoVendaProdutoValidator(precoVenda, validator)

                if (erros != null) {
                    Text(erros)
                }
            }
        )

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
                val produto = Produto(
                    nomeProduto,
                    quantidade.toIntOrNull(),
                    precoCusto.toDoubleOrNull(),
                    precoVenda.toDoubleOrNull(),
                    marca
                )
                validator = true
                if (onSave(produto)) {
                    message = "Salvo com sucesso"
                    val path = "/${produto.nome}/${produto.quantidade}/${produto.precoCusto}/${produto.precoVenda}/${produto.marca}"
                    navController.navigate(MarketScreen.InfoProduto.toString()+path)
                } else {
                    message = "Prencha os campos"
                }

            }) {
                Text("Salvar")
            }

            OutlinedButton(onClick = {
                nomeProduto = "";
                quantidade = "";
                precoCusto = "";
                precoVenda = "";
                marca = "";
                validator = false
                message = "";
            }) {
                Text("Cancelar")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(message)
        }


    }
}

fun nomeProdutoValidator(value : String?, validator : Boolean): String? {
    if (value.isNullOrEmpty() && validator) {
        return "O nome do produto não pode ser vazio";
    }

    if (value!!.length <= 3 && validator) {
        return "O nome do produto deve conter 3 caracteres";
    }
    return null
}

fun quantidadeProdutoValidator(value : String?, validator : Boolean): String? {
    if ((value.isNullOrBlank() || value == "null") && validator) {
        return "A quantidade do produto não pode ser vazio";
    }

    if (value!!.isEmpty()  && validator) {
        return "A quantidade do produto não pode ser vazio";
    }

    if (value.isNotEmpty() && value.toInt() < 0) {
        return "Informe uma quantidade maior que 0"
    }

    return null;
}

fun precoCustoProdutoValidator(value : String?, validator : Boolean): String? {
    if ((value.isNullOrBlank() || value == "null") && validator) {
        return "O preço custo do produto não pode ser vazio"
    }

    if (value.isNullOrEmpty() && validator) {
        return "O preço custo do produto não pode ser vazio"
    }

    if (value!!.isNotEmpty() && value.toDouble() < 0) {
        return "Informe um custo produto maior que 0"
    }

    return null;
}

fun precoVendaProdutoValidator(value : String, validator : Boolean) : String? {
    if (value.isEmpty()) {
        return null
    }

    if (value != "null" && value.toDouble() < 0) {
        return "Informe um preço venda maior que 0"
    }

    return null;
}


fun onSave(produto : Produto) : Boolean {
    //NOME MAIOR QUE 3 CARACTES
    //QTD MAIOR QUE 0
    //PREÇO DO CUSTO > 0 E != DE NULL
    //PREÇO DE VENDA PODE SER NULL > 0
    val  nomeProdutoValidator = nomeProdutoValidator(produto.nome, true) != null
    val  quantidadeProdutoValidator = quantidadeProdutoValidator(produto.quantidade.toString(), true) != null
    val  precoCustoProdutoValidator =precoCustoProdutoValidator(produto.precoCusto.toString(), true) != null
    val  precoVendaProdutoValidator = precoVendaProdutoValidator(produto.precoVenda.toString(), true) != null

    return !(nomeProdutoValidator || quantidadeProdutoValidator || precoCustoProdutoValidator || precoVendaProdutoValidator);
}