package com.example.cadastros.classes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

class ProductInfoInputHandler(var errorsMap: Map<String, String>, var useInitialState: Boolean) {

    @Composable
    fun NomeProduto(value: String, onValueChange: (String) -> Unit) {
        this.InnerHandler(
            textLabel = "Nome do produto",
            textValue = value,
            errorKey = "nome",
            onValueChange = { onValueChange(it) }
        )
    }

    @Composable
    fun Quantidade(value: String, onValueChange: (String) -> Unit) {
        this.InnerHandler(
            textLabel = "Quantidade em estoque",
            textValue = value,
            errorKey = "quantidade",
            onValueChange = { onValueChange(it) }
        )
    }

    @Composable
    fun PrecoCusto(value: String, onValueChange: (String) -> Unit) {
        this.InnerHandler(
            textLabel = "Preço de custo (opcional)",
            textValue = value,
            errorKey = "precoCusto",
            onValueChange = { onValueChange(it) }
        )
    }

    @Composable
    fun PrecoVenda(value: String, onValueChange: (String) -> Unit) {
        this.InnerHandler(
            textLabel = "Preço de venda",
            textValue = value,
            errorKey = "precoVenda",
            onValueChange = { onValueChange(it) }
        )
    }

    @Composable
    fun Marca(value: String, onValueChange: (String) -> Unit) {
        this.InnerHandler(
            textLabel = "Nome da marca (opcional)",
            textValue = value,
            errorKey = "marca",
            onValueChange = { onValueChange(it) }
        )
    }

    @Composable
    private fun InnerHandler(textLabel: String, textValue: String, errorKey: String, onValueChange: (String) -> Unit, keyboardType: KeyboardType? = null) {
        Field(
            textLabel = textLabel,
            textValue = textValue,
            onValueChange = { onValueChange(it) },
            valueIsOk = this.errorsMap.getOrDefault(errorKey, "") == "",
            useInitialState = this.useInitialState,
            keyboardType = keyboardType
        )
        ErrorStatus(this.errorsMap[errorKey])
        this.Spacer()
    }

    @Composable
    private fun ErrorStatus(errorText: String?) {
        errorText?.let { Text(text = it, color = Color.Red) }
    }

    @Composable
    private fun Spacer(height: Int = 16) {
        androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(height.dp))
    }

    @Composable
    private fun Field(
        textLabel: String,
        textValue: String,
        onValueChange: (String) -> Unit,
        valueIsOk: Boolean = true,
        useInitialState: Boolean = false,
        keyboardType: KeyboardType? = null,
        modifier: Modifier = Modifier
    ) {
        val color = when {
            useInitialState -> Color.Black
            !valueIsOk -> Color.Red
            textValue.isBlank() -> Color.Black
            else -> Color(0xFF228300)
        }

        OutlinedTextField(
            value = textValue,
            onValueChange = { onValueChange(it) },
            label = { Text(textLabel) },
            keyboardOptions = if (keyboardType == null) KeyboardOptions.Default else KeyboardOptions(keyboardType = keyboardType),
            modifier = modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = color,
                focusedBorderColor = color,
                unfocusedLabelColor = color,
                focusedLabelColor = color
            )
        )
    }

}