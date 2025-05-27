package com.example.cadastros

import java.math.BigDecimal

class FakeProdutoViewModel : ProdutoViewModel() {
    init {
        _produtos.value = listOf(
            Produto(id = 1, "Arroz", 10, BigDecimal.valueOf(5.0), BigDecimal.valueOf(6.5), "Supermercado"),
            Produto(id = 2, "Feijão", 5, BigDecimal.valueOf(4.0), BigDecimal.valueOf(5.5), "Market")
        )
    }
}