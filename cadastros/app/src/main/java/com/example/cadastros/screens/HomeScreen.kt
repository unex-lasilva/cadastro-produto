package com.example.cadastros.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.cadastros.classes.MarketScreen
import com.example.cadastros.objects.Defaults.Background

@Composable
fun HomeScreen(navController: NavController) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Background()
        InnerContent(navController)
    }
}

@Composable
private fun InnerContent(navegador: NavController) {
    Row (horizontalArrangement = Arrangement.Absolute.SpaceBetween, modifier = Modifier.fillMaxWidth(0.8f)) {
        Option("Cadastro", MarketScreen.Register.toString(), navegador)
        Option("Registros", MarketScreen.Products.toString(), navegador)
    }
}

@Composable
private fun Option(optionLabel: String, destination: String, navController: NavController) {
    val size = 130
    ElevatedButton (
        onClick = { navController.navigate(destination) },
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(width = 1.5.dp, color = Color.Black),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White
        ),
        modifier = Modifier.height(size.dp).width(size.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) { 
            Text(text = optionLabel, color = Color.Black, fontSize = 18.sp)
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}