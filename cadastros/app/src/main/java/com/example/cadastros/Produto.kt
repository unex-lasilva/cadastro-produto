package com.example.cadastros

import java.math.BigDecimal

class Produto(
    val nome: String = "",
    val quantidade: Int = 0,
    val precoCusto: BigDecimal = BigDecimal(0.0),
    val precoVenda: BigDecimal = BigDecimal(0.0),
    val marca: String = ""
){

    override fun toString(): String {
        return "[Nome: $nome, Quantidade: $quantidade, Preço Custo: $precoCusto, Preço Venda: $precoVenda, Marca: $marca]"
    }

}