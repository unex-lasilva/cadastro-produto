package com.example.cadastros

import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import android.app.Activity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight


@SuppressLint("ContextCastToActivity")
@Composable
fun HomeScreen(navController: NavController) {

    val activity = LocalContext.current as? Activity

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Tela Inicial",
            color = Color.DarkGray,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            navController.navigate(MarketScreen.CadastroProduto.toString())
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ),
            border = BorderStroke(2.dp, Color(0xFF4F8651)),
            shape = RoundedCornerShape(5.dp)
        )
        {
            Text("Cadastrar Produto")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            navController.navigate(MarketScreen.InfoProduto.toString())
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9800),
                contentColor = Color.White,
            ),
            border = BorderStroke(2.dp, Color(0xFFB48033)),
            shape = RoundedCornerShape(5.dp)
            )
        {
            Text("Produtos Cadastrados")
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(onClick = {
            activity?.finish()
        },
            border = BorderStroke(2.dp, Color.Gray),
            shape = RoundedCornerShape(5.dp)
            ) {
            Text("Sair", color = Color.DarkGray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}
