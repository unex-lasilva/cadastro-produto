package com.example.cadastros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.cadastros.model.ProdutoViewModel

class MainActivity : ComponentActivity() {
    private val produtoViewModel: ProdutoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CadastroProdutoScreen(viewModel = produtoViewModel)
        }
    }
}
