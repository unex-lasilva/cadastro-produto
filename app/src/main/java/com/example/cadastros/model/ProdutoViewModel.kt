package com.example.cadastros.model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.cadastros.data.Produto

class ProdutoViewModel : ViewModel() {
    fun salvarProduto(produto: Produto) {
        Log.d("ProdutoViewModel", "Produto salvo: $produto")
    }
}
