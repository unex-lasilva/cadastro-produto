//package com.example.cadastros
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.tooling.preview.Preview
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
//
//class SegundaTela : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            SegundaTelaScreen()
//        }
//    }
//}
//
//
//@Composable
//fun SegundaTelaScreen(){
//    val intent = Intent(context, CadastroProdutoScreen::class.java)
//    val bundle = Bundle()
//
//    val nome = intent.getStringExtra("nome")
//    val quantidade = intent.getStringExtra("quantidade")
//    val precoCusto = intent.getStringExtra("precoCusto")
//    val precoVenda = intent.getStringExtra("precoVenda")
//    val marca = intent.getStringExtra("marca")
//
//}
//
//@Preview
//@Composable
//fun SegundaTelaScreenView(){
//    SegundaTelaScreen()
//}