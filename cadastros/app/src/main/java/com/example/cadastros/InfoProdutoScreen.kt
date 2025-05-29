package com.example.cadastros

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun InfoProdutoScreen(navController: NavController, produto: Produto) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxSize(fraction = 0.9f),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                InfoItem(label = "Nome", value = produto.nome)
                InfoItem(label = "Quantidade", value = produto.quantidade.toString())
                InfoItem(label = "Preço de Venda", value = "R$ %.2f".format(produto.precoVenda))
                InfoItem(label = "Preço de Custo", value = "R$ %.2f".format(produto.precoCusto))
                InfoItem(label = "Marca", value = produto.marca!!)

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Voltar")
                }
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        )
    }
}
