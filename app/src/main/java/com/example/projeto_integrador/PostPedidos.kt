package com.example.projeto_integrador

// Data class que representa um item do pedido
data class ItemPedidoCadastrar(
    val observacoes: String,
    val idProduto: Int
)

// Data class que representa o pedido completo
data class PedidoCadastrar(
    val mesa: Int,
    val comanda: String,
    val itensPedido: List<ItemPedidoCadastrar>
)
