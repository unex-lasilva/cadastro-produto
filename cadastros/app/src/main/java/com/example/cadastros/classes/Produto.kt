package com.example.cadastros.classes

import java.math.BigDecimal

class Produto(nome: String, quantidade: String, precoCusto: String, precoVenda: String, marca: String) {
    val nome: String
    var quantidade: Int
    var precoCusto: BigDecimal?
    var precoVenda: BigDecimal
    val marca: String?
    
    init {
        if (Validador.naoEstaOk(nome, quantidade, precoCusto, precoVenda))
            throw IllegalArgumentException("Valores inválidos encontrados ao instanciar o Produto.")
        this.nome = Formatador.nome(nome)
        this.quantidade = Formatador.quantidade(quantidade) ?: throw IllegalArgumentException("Quantidade inválida ao instanciar o Produto.")
        this.precoVenda = Formatador.precoVenda(precoVenda) ?: throw IllegalArgumentException("Preço de venda inválido ao instanciar o Produto.")
        this.precoCusto = if (precoCusto == "") null else Formatador.precoCusto(precoCusto) ?: throw IllegalArgumentException("Preço de custo inválido ao instanciar o Produto.")
        this.marca = if (marca == "") null else Formatador.marca(marca)
    }

    object Formatador {
        fun nome(nome: String) = nome.trim().replaceFirstChar { it.uppercase() }
        fun quantidade(quantidade: String) = quantidade.trim().toIntOrNull()
        fun precoCusto(precoCusto: String) = precoCusto.trim().replace(',', '.').toBigDecimalOrNull()
        fun precoVenda(precoVenda: String) = precoVenda.trim().replace(',', '.').toBigDecimalOrNull()
        fun marca(marca: String) = marca.trim()
    }

    object Validador {

        // Métodos gerais, para obter status sobre todos os atributos de uma vez

        operator fun invoke(nome: String, quantidade: String, precoCusto: String, precoVenda: String): Map<String, String> {
            return mapOf(
                "nome" to this.nome(nome),
                "quantidade" to this.quantidade(quantidade),
                "precoCusto" to this.precoCusto(precoCusto),
                "precoVenda" to this.precoVenda(precoVenda)
            )
        }

        fun estaOk(nome: String, quantidade: String, precoCusto: String, precoVenda: String): Boolean {
            this(nome, quantidade, precoCusto, precoVenda).forEach {
                if (it.value !== "") {
                    return false
                }
            }
            return true
        }

        fun naoEstaOk(nome: String, quantidade: String, precoCusto: String, precoVenda: String): Boolean {
            return !this.estaOk(nome, quantidade, precoCusto, precoVenda)
        }
        
        // Métodos para validação de cada atributo pertencente ao Produto

        fun nome(nome: String): String {
            val nomeAparado = nome.trim()
            return when {
                nomeAparado.isBlank() -> "Nome do produto deve ser informado!"
                nomeAparado.length < 3 -> "Nome do produto deve conter 3 ou mais caracteres!"
                else -> ""
            }
        }

        fun quantidade(quantidade: String): String {
            val quantidadeAparada = quantidade.trim()
            val quantidadeFormatada = Formatador.quantidade(quantidadeAparada)
            return when {
                quantidadeAparada.isBlank() -> "Quantidade deve ser informada!"
                quantidadeFormatada == null -> "Corrija a quantidade informada!"
                quantidadeFormatada <= 0 -> "Cadastre somente produtos com 1 ou mais itens em estoque!"
                else -> ""                
            }
        }

        fun precoCusto(precoCusto: String): String {
            val precoCustoFormatado = Formatador.precoCusto(precoCusto) 
            return when {
                precoCusto.isEmpty() -> ""
                precoCustoFormatado == null || precoCustoFormatado <= BigDecimal(0) -> "Corrija o preço de custo informado!" 
                else -> ""
            }
        }

        fun precoVenda(precoVenda: String): String {
            val precoVendaAparado = precoVenda.trim()
            val precoVendaFormatado = Formatador.precoVenda(precoVenda)
            return when {
                precoVendaAparado.isBlank() -> "Preço de venda deve ser informado!"
                precoVendaFormatado == null || precoVendaFormatado <= BigDecimal(0) -> "Corrija o preço de venda informado!"
                else -> ""
            }
        }
        
    }

}