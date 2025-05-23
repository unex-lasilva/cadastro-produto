package com.example.cadastros.data

data class Produto(
    val nomeProduto: String,
    val quantidade: Int,
    val precoCusto: Double,
    val precoVenda: Double,
    val marca: String? = null
)