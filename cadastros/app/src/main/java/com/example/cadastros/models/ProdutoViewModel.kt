package com.example.cadastros.models

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.example.cadastros.classes.Produto

class ProdutoViewModel : ViewModel() {
    private var proxId = 0
    private val produtos = mutableStateMapOf<Int, Produto>()
    
    fun adicionar(nome: String, quantidade: String, precoCusto: String, precoVenda: String, marca: String) {
        produtos[++proxId] = Produto(nome, quantidade, precoCusto, precoVenda, marca)
    }
    
    fun buscar(id: Int): Produto? {
        return this.produtos[id]
    }
    
    fun verTodos(): Map<Int, Produto> {
        return this.produtos
    }
}