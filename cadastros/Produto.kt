package com.example.cadastros.model

data class Produto(
    val nome: String,
    val quantidade: Int,
    val precoCusto: Double,
    val precoVenda: Double,
    val marca: String = ""
)