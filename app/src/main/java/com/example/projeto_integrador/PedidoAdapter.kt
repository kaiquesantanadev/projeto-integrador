package com.example.projeto_integrador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PedidoAdapter(private val pedidos: List<Pedido>) :
    RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    inner class PedidoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvComanda: TextView = itemView.findViewById(R.id.tvComanda)
        val tvMesa: TextView = itemView.findViewById(R.id.tvMesa)
        val tvDataHora: TextView = itemView.findViewById(R.id.tvDataHora)
        val tvItens: TextView = itemView.findViewById(R.id.tvItens)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pedido, parent, false)
        return PedidoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.tvComanda.text = "Comanda: ${pedido.comanda}"
        holder.tvMesa.text = "Mesa: ${pedido.mesa}"
        holder.tvDataHora.text = "Data/Hora: ${pedido.dataHoraPedido}"

        val itens = pedido.itensPedido.joinToString(", ") { "${it.produto.nome} (${it.observacao})" }
        holder.tvItens.text = "Itens: $itens"
    }

    override fun getItemCount(): Int {
        return pedidos.size
    }
}
