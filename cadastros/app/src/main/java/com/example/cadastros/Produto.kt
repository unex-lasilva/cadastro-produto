package com.example.cadastros

import java.math.BigDecimal

data class Produto (
    val id: Int, val nome: String, val qtd: Int, val precoProduto: BigDecimal, val precoVenda: BigDecimal, val marca: String
)