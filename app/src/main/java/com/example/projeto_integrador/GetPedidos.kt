package com.example.projeto_integrador

data class Pedido(
    val id: Int,
    val mesa: Int,
    val comanda: String,
    val dataHoraPedido: String,
    val itensPedido: List<ItemPedido>
)

data class ItemPedido(
    val id: Int,
    val observacao: String,
    val produto: Produto,
    val controleStatusItemPedidoDtoDetalhar: ControleStatusItemPedidoDto
)

data class Produto(
    val id: Int,
    val nome: String,
    val descricao: String,
    val preco: Double,
    val linkImagem: String,
    val tipoProduto: TipoProduto
)

data class TipoProduto(
    val id: Int,
    val nome: String
)

data class ControleStatusItemPedidoDto(
    val id: Int,
    val status: Status,
    val descricao: String,
    val dataHoraIniciado: String,
    val dataHoraPronto: String? = null
)

data class Status(
    val id: Int,
    val status: String,
    val descricao: String
)

data class PaginatedResponse(
    val content: List<Pedido>
)
