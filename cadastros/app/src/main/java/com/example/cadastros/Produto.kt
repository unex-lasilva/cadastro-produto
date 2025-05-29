package com.example.cadastros

import java.math.BigDecimal

// criação de uma data class que é uma classe feita para armazenar dados, com funções feitas para a manipulação desses dados
data class Produto (
    val id: Int, val nome: String, val qtd: Int, val precoProduto: BigDecimal, val precoVenda: BigDecimal, val marca: String
)