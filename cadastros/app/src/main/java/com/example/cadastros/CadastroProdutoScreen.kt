package com.example.cadastros

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.unregisterForAllProfilingResults
import com.example.cadastros.classes.Produto
import java.math.BigDecimal

@Composable
fun CadastroProdutoScreen() {

    var nomeProduto by remember { mutableStateOf("") }
    var quantidade by remember { mutableStateOf("") }
    var precoCusto by remember { mutableStateOf("") }
    var precoVenda by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }

    var nomeProdutoErro by remember { mutableStateOf("") }
    var quantidadeErro by remember { mutableStateOf("") }
    var precoCustoErro by remember { mutableStateOf("") }
    var precoVendaErro by remember { mutableStateOf("") }

    var cadastroStatus by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Título
        Text(
            text = "Cadastro de produto",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 16.dp, start = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Nome do Produto
        Field(
            textLabel = "Nome do Produto",
            textValue = nomeProduto,
            valueIsOk = nomeProdutoErro.isBlank(),
            onValueChange = {
                nomeProduto = it
                val erros = ErrorGetter(nomeProduto, quantidade, precoCusto, precoVenda)
                nomeProdutoErro = erros[0]
                quantidadeErro = erros[1]
                precoCustoErro = erros[2]
                precoVendaErro = erros[3]
            }
        )
        Text(text = nomeProdutoErro, color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))


        // Quantidade em estoque
        Field(
            textLabel = "Quantidade em estoque",
            textValue = quantidade,
            keyboardType = KeyboardType.Number,
            onValueChange = {
                quantidade = it
                val erros = ErrorGetter(nomeProduto, quantidade, precoCusto, precoVenda)
                nomeProdutoErro = erros[0]
                quantidadeErro = erros[1]
                precoCustoErro = erros[2]
                precoVendaErro = erros[3]
            },
            valueIsOk = quantidadeErro.isBlank()
        )
        Text(text = quantidadeErro, color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))


        // Preço de custo
        Field(
            textLabel = "Preço de custo",
            textValue = precoCusto,
            keyboardType = KeyboardType.Decimal,
            onValueChange = {
                precoCusto = it
                val erros = ErrorGetter(nomeProduto, quantidade, precoCusto, precoVenda)
                nomeProdutoErro = erros[0]
                quantidadeErro = erros[1]
                precoCustoErro = erros[2]
                precoVendaErro = erros[3]
            },
            valueIsOk = precoCustoErro.isBlank()
        )
        Text(text = precoCustoErro, color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))

        // Preço de venda
        Field(
            textLabel = "Preço de venda",
            textValue = precoVenda,
            keyboardType = KeyboardType.Decimal,
            onValueChange = {
                precoVenda = it
                val erros = ErrorGetter(nomeProduto, quantidade, precoCusto, precoVenda)
                nomeProdutoErro = erros[0]
                quantidadeErro = erros[1]
                precoCustoErro = erros[2]
                precoVendaErro = erros[3]
            },
            valueIsOk = precoVendaErro.isBlank()
        )
        Text(text = precoVendaErro, color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))

        // Nome da marca
        Field(
            textLabel = "Nome da marca",
            textValue = marca,
            onValueChange = { marca = it },
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botões
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = {
                cadastroStatus = ErrorGetter(nomeProduto, quantidade, precoCusto, precoVenda).joinToString("")
                if (cadastroStatus.isBlank()) {
                    cadastroStatus = "OK!"
                } else {
                    cadastroStatus = "Preencha os campos necessários e corrija os erros existentes!"
                }
            }) {
                Text("Salvar")
            }

            OutlinedButton(onClick = {
                // Ação de cancelar
            }) {
                Text("Cancelar")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))


        Text(text = cadastroStatus, color = if (cadastroStatus == "OK!") Color.Green else Color.Red)
    }


}

@Preview(showBackground = true)
@Composable
fun CadastroProdutoPreview() {
    CadastroProdutoScreen()
}

@Composable
fun Field(
    modifier: Modifier = Modifier,
    textLabel: String,
    textValue: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit,
    valueIsOk: Boolean = true
) {
    val color = when {
        !valueIsOk -> Color.Red
        textValue.isNotBlank() -> Color(0xFF0E3400)
        else -> Color.Gray
    }

    OutlinedTextField(
        value = textValue,
        onValueChange = { onValueChange(it) },
        label = { Text(textLabel) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = modifier,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = color.copy(alpha = 0.7f),
            unfocusedLabelColor = color.copy(alpha = 0.7f),
            focusedBorderColor = color,
            focusedLabelColor = color
        )
    )
}


object ErrorGetter {

    operator fun invoke(
        nomeProduto: String,
        qntProduto: String,
        precoCusto: String,
        precoVenda: String
    ): Array<String> {
        return arrayOf(
            ErrorGetter.nomeProduto(nomeProduto),
            ErrorGetter.qntEstoque(qntProduto),
            ErrorGetter.precoCusto(precoCusto),
            ErrorGetter.precoVenda(precoVenda)
        )
    }

    fun nomeProduto(nomeProduto: String): String {
        return if (nomeProduto.length < 3) {
            "Nome do produto tem menos de 3 caracteres"
        } else {
            ""
        }
    }

    fun qntEstoque(qntEstoque: String): String {
        val qntEstoqueInt = qntEstoque.toIntOrNull()
        return if (qntEstoqueInt == null || qntEstoqueInt <= 0) {
            "Quantidade em estoque inválida"
        } else {
            ""
        }
    }

    fun precoCusto(precoCusto: String): String {
        val precoCustoBigDecimal = precoCusto.toBigDecimalOrNull()
        return if (precoCustoBigDecimal != null && precoCustoBigDecimal <= BigDecimal(0)) {
            "Preço de custo inválido"
        } else {
            ""
        }
    }

    fun precoVenda(precoVenda: String): String {
        val precoVendaBigDecimal = precoVenda.toBigDecimalOrNull()
        return if (precoVendaBigDecimal != null && precoVendaBigDecimal <= BigDecimal(0)) {
            "Preço de venda inválido"
        } else {
            ""
        }
    }
}
