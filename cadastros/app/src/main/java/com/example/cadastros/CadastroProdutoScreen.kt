package com.example.cadastros

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.window.Popup
import java.math.BigDecimal

@Composable
fun CadastroProdutoScreen() {

    var nomeProduto by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var precoCusto by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }

    var ErrnomeProduto by remember { mutableStateOf(false) }
    var Errquantidade by remember { mutableStateOf(false) }
    var ErrprecoCusto by remember { mutableStateOf(false) }
    var ErrprecoVenda by remember { mutableStateOf(false) }
    var Errmarca by remember { mutableStateOf(false) }
    var salvar by remember { mutableStateOf(false) }

    var produto by remember { mutableStateOf<Produto?>(null) }


//    val intent = Intent(context, SegundaTela::class.java)
//    val bundle = Bundle()
//
//    bundle.putString("nome", produto.nome)
//    bundle.putString("quantidade", produto.quantidade)
//    bundle.putString("precoCusto", produto.precoCusto)
//    bundle.putString("precoVenda", produto.precoVenda)
//    bundle.putString("marca", produto.marca)
//
//    intent.putExtras(bundle)
//    context.startActivity(intent)
//
//




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
            modifier = Modifier.fillMaxWidth()
        )
        if (ErrnomeProduto) {
            if (nomeProduto.length < 3){
                Text(
                    text = "Nome é obrigatório com 3 digitos!",
                    color = Color.Red
                )

            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        // Quantidade em estoque
        OutlinedTextField(
            value = quantidade,
            onValueChange = { quantidade = it },
            label = { Text("Quantidade em estoque") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        if (Errquantidade) {
            val quantidadeInt = quantidade.toIntOrNull()

            if (quantidade.isBlank()) {
                Text(
                    text = "Quantidade é obrigatória!",
                    color = Color.Red
                )
            } else if (quantidadeInt == null || quantidadeInt <= 0) {
                Text(
                    text = "Quantidade tem que ser maior que 0",
                    color = Color.Red
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Preço de custo
        OutlinedTextField(
            value = precoCusto,
            onValueChange = { precoCusto = it },
            label = { Text("Preço de custo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        if(ErrprecoCusto){
            if (!precoCusto.isBlank() && precoCusto.toIntOrNull()?.let { it <= 0 } ?: true){
                Text(
                    text = "Preço de Custo não pode ser 0!",
                    color = Color.Red
                )
            }



        }

        Spacer(modifier = Modifier.height(16.dp))

        // Preço de venda
        OutlinedTextField(
            value = precoVenda,
            onValueChange = { precoVenda = it },
            label = { Text("Preço de venda") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
            isError = ErrprecoVenda != null ?: false
        )
        if(ErrprecoVenda){
            if (precoVenda.isBlank()){
                Text(
                    text = "Preço de Venda é obrigatoria!",
                    color = Color.Red
                )
            }else{
                if (precoVenda.toIntOrNull()?.let { it <= 0 } ?: true){
                    Text(
                        text = "Preço de Venda não pode ser 0!",
                        color = Color.Red
                    )
                }
            }

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
        if (salvar){
            Text("Salvando produto: ${produto}")
            Popup()

        }




        Spacer(modifier = Modifier.height(32.dp))

        // Botões
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {

            Button(onClick = {

                ErrnomeProduto = nomeProduto.isBlank() || nomeProduto.length < 3
                Errquantidade = quantidade.isBlank() || quantidade.toIntOrNull()?.let { it <= 0 } ?: true
                ErrprecoCusto = precoCusto.toBigDecimalOrNull()?.let { it <= BigDecimal(0) } ?: true
                ErrprecoVenda = precoVenda.toBigDecimalOrNull()?.let { it <= BigDecimal(0) } ?: true

                if (!ErrnomeProduto && !Errquantidade && !ErrprecoCusto && !ErrprecoVenda) {
                    salvar = true
                    produto = Produto(nomeProduto, quantidade.toIntOrNull() ?: 0, precoCusto.toBigDecimalOrNull() ?: BigDecimal.ZERO, precoVenda.toBigDecimalOrNull() ?: BigDecimal.ZERO, marca)
                    nomeProduto = ""
                    quantidade = ""
                    precoCusto = ""
                    precoVenda = ""
                    marca = ""
                }else{
                    salvar = false
                }



            }) {
                Text("Salvar")
            }


            OutlinedButton(onClick = {
                // Ação de cancelar
                nomeProduto = ""
                quantidade = ""
                precoCusto = ""
                precoVenda = ""
                marca = ""
                salvar = false
                ErrnomeProduto = false
                Errquantidade =false
                ErrprecoCusto =false
                ErrprecoVenda =false
                Errmarca =false

            }) {
                Text("Cancelar")
            }

        }

    }
}


@Composable
fun Popup() {
    var mostrarPopup by remember { mutableStateOf(true) }

    Column {

        if (mostrarPopup) {
            AlertDialog(
                onDismissRequest = { mostrarPopup = false },
                title = { Text(text = "Produto Salvo!") },
                text = { Text("O produto foi salvo com sucesso!") },
                confirmButton = {
                    Button(onClick = { mostrarPopup = false }) {
                        Text("OK")
                    }
                }
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun CadastroProdutoPreview() {
    CadastroProdutoScreen()
}
