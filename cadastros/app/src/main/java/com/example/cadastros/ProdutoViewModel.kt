package com.example.cadastros

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// classe que irá manter os dados persistentes enquanto a tela estiver existindo na aplicação
open class ProdutoViewModel: ViewModel() {

    // Lista de produtos com StateFlow (observável)
    internal val _produtos = MutableStateFlow<List<Produto>>(emptyList())
    val produtos = _produtos.asStateFlow()

    private var ultimoId = 0

    // Função para adicionar um produto
    fun adicionarProduto(produto: Produto) {
        val produtoComId = produto.copy(id = ++ultimoId)
        _produtos.value = _produtos.value + produtoComId
    }

    fun removerProduto(produto: Produto){
        val listaAtual = _produtos.value.toMutableList()
        listaAtual.remove(produto)
        _produtos.value = listaAtual
    }
}