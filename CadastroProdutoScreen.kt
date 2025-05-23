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

@Composable
fun CadastroProdutoScreen() {

    var nomeProduto by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var precoCusto by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }

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
            label = { Text("Nome do Produto") },
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth()
        )



        Spacer(modifier = Modifier.height(16.dp))

        // Quantidade em estoque
        OutlinedTextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade em estoque") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))



        // Preço de custo
        OutlinedTextField(
            value = precoCusto,
            onValueChange = { precoCusto = it },
            label = { Text("Preço de custo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )



        Spacer(modifier = Modifier.height(16.dp))

        // Preço de venda
        OutlinedTextField(
            value = precoVenda,
            onValueChange = { precoVenda = it },
            label = { Text("Preço de venda") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
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
                // Ação de salvar
            }) {
                Text("Salvar")
            }

            OutlinedButton(onClick = {
                // Ação de cancelar
            }) {
                Text("Cancelar")
            }
        }
    }
}

//validação do nome do produto
fun validarNomeProduto(nome: String): Boolean {
    if (nome.isBlank()) {
        return false
    }

    if (nome.length < 3) {
        return false
    }
    return true
}

//Validação da Quantidade Em Estoque
fun validarQuantidadeemEstoque(input: String): Boolean {
    val quantidade = input.toIntOrNull()

    if (quantidade == null) {
        print("Quantidade Invalida. Digite um numero inteiro.")
        return false
    }

    if (quantidade == 0 ) {
        println("Quantidade eh igual a 0")
        return false
    }

    return false
}

// Validação do preço de custo
fun ValidarPrecoCusto(input: String): Boolean {
    val preco = input.toDoubleOrNull()

    if (preco == null) {
        println("Preco invalido. Digite um numero valido com ponto ou virgula")
    }
    if (preco == 0.0) {
        println("Preco eh igual a 0")
    }


//validação de preço de venda
fun PrecoVenda(precoVendaStr: String, precoCustoStr: String): Boolean {
    val precoVenda = precoVendaStr.replace(",", ".").toDoubleOrNull()
    val precoCusto = precoVendaStr.replace(",",".").toDoubleOrNull()

    if (precoVenda == null) {
        println("Preco de venda invalido")
        return false
    }

    if (precoVenda == 0.0) {
        println ("Preco de venda eh zero")
        return false
    }
    return false
}

data class Produto(val nome : String,
    val qtd : Int)

val produto1 = Produto("Tiago", 2)
    return false}

@Composable
//Classe NomeProduto
fun NomeProdutoField(
    nomeProduto: String,
    onNomeChange: (String) -> Unit
) {
    OutlinedTextField(
        value = nomeProduto,
        onValueChange = onNomeChange,
        label = { Text("Nome do Produto") },
        keyboardOptions = KeyboardOptions.Default,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
//Classe QuantidadeEstoque
fun QuantidadeEstoqueField(
    quantidade: String,
    onQuantidadeChange: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            value = quantidade,
            onValueChange = onQuantidadeChange,
            label = { Text("Quantidade em estoque") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
//Classe PreçoCusto
fun PrecoCustoField(
    precoCusto: String,
    onPrecoCustoChange: (String) -> Unit
) {
    OutlinedTextField(
        value = precoCusto,
        onValueChange = onPrecoCustoChange,
        label = { Text("Preço de custo") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
//Classe Preço Venda
fun PrecoVendaField(
    precoVenda: String,
    onPrecoVendaChange: (String) -> Unit
) {
    OutlinedTextField(
        value = precoVenda,
        onValueChange = onPrecoVendaChange,
        label = { Text("Preço de venda") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
//Classe Nome Marca
fun NomeMarcaField(
    marca: String,
    onMarcaChange: (String) -> Unit
) {
    OutlinedTextField(
        value = marca,
        onValueChange = onMarcaChange,
        label = { Text("Nome da marca") },
        keyboardOptions = KeyboardOptions.Default,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(32.dp))
}

object NomeProduto {

    @Composable
    fun NomeProdutoField(
        nomeProduto: String,
        onNomeChange: (String) -> Unit
    ) {
        OutlinedTextField(
            value = nomeProduto,
            onValueChange = onNomeChange,
            label = { Text("Nome do Produto") },
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

object QuantidadeEstoque {

    @Composable
    fun QuantidadeEstoqueField(
        quantidade: String,
        onQuantidadeChange: (String) -> Unit
    ) {
        Column {
            OutlinedTextField(
                value = quantidade,
                onValueChange = onQuantidadeChange,
                label = { Text("Quantidade em estoque") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

}

object PrecoCusto {

    @Composable
    fun PrecoCustoField(
        precoCusto: String,
        onPrecoCustoChange: (String) -> Unit
    ) {
        OutlinedTextField(
            value = precoCusto,
            onValueChange = onPrecoCustoChange,
            label = { Text("Preço de custo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

object PrecoVenda {

    @Composable
    fun PrecoVendaField(
        precoVenda: String,
        onPrecoVendaChange: (String) -> Unit
    ) {
        OutlinedTextField(
            value = precoVenda,
            onValueChange = onPrecoVendaChange,
            label = { Text("Preço de venda") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

object NomeMarca {

    @Composable
    fun NomeMarcaField(
        marca: String,
        onMarcaChange: (String) -> Unit
    ) {
        OutlinedTextField(
            value = marca,
            onValueChange = onMarcaChange,
            label = { Text("Nome da marca") },
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}