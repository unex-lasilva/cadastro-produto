package com.example.cadastros

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// classe que irá manter os dados persistentes enquanto a tela estiver existindo na aplicação
// open permite que a classe possa ser herdada, caso necessário
open class ProdutoViewModel: ViewModel() {

    // lista mutável e observavel assim notificando quando ocorrer uma mudança com o produto
    // o uso do underline significa a privação do uso das variaveis para não serem usadas externamente
    // internal para garantir o uso fora do meu objeto, mas somente no mesmo módulo
    internal val _produtos = MutableStateFlow<List<Produto>>(emptyList())
    val produtos = _produtos.asStateFlow() // versão somente leitura da lista de produtos para o resto do app

    private var ultimoId = 0

    // Função para adicionar um produto
    fun adicionarProduto(produto: Produto) {
        val produtoComId = produto.copy(id = ++ultimoId) // usa o copy para copiar o produto, mas com a alteração de valor apenas no id
        _produtos.value = _produtos.value + produtoComId
    }

    // função para remover um produto
    fun removerProduto(produto: Produto){
        val listaAtual = _produtos.value.toMutableList() // cria uma copia da lista dos produtos em uma versão editável/mutavel
        listaAtual.remove(produto) // remoção do produto na lista atual
        _produtos.value = listaAtual // atualiza o valores da lista para a nova versão da lista com o produto já removido
    }
}