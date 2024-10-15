package com.example.projeto_integrador

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class PedidoCadastrarScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.criarpedidos)

        val pratoEditText = findViewById<EditText>(R.id.pratoID)
        val bebidaEditText = findViewById<EditText>(R.id.bebidaID)
        val sobremesaEditText = findViewById<EditText>(R.id.sobremesaID)
        val mesaEditText = findViewById<EditText>(R.id.input_mesa)
        val comandaEditText = findViewById<EditText>(R.id.input_comanda)
        val observacoesPratoEditText = findViewById<EditText>(R.id.obs_pratos)
        val observacoesBebidaEditText = findViewById<EditText>(R.id.obs_bebidas)
        val observacoesSobremesaEditText = findViewById<EditText>(R.id.obs_sobremesas)

        val buttonSubmit = findViewById<Button>(R.id.button_submit)

        buttonSubmit.setOnClickListener {
            // Coleta os dados dos campos
            val mesa = mesaEditText.text.toString().toInt()
            val comanda = comandaEditText.text.toString()

            val prato = ItemPedidoCadastrar(
                observacoes = observacoesPratoEditText.text.toString(),
                idProduto = pratoEditText.text.toString().toInt()
            )

            val bebida = ItemPedidoCadastrar(
                observacoes = observacoesBebidaEditText.text.toString(),
                idProduto = bebidaEditText.text.toString().toInt()
            )

            val sobremesa = ItemPedidoCadastrar(
                observacoes = observacoesSobremesaEditText.text.toString(),
                idProduto = sobremesaEditText.text.toString().toInt()
            )

            val pedido = PedidoCadastrar(
                mesa = mesa,
                comanda = comanda,
                itensPedido = listOf(prato, bebida, sobremesa)
            )

            enviarPedido(pedido)
        }
    }

    private fun enviarPedido(pedido: PedidoCadastrar) {
        thread {
            try {
                // Configurando a URL da API
                val url = URL("http://10.0.2.2:8080/pedido")
                val httpURLConnection = url.openConnection() as HttpURLConnection

                // Definindo o método e os cabeçalhos da requisição
                httpURLConnection.requestMethod = "POST"
                httpURLConnection.setRequestProperty("Content-Type", "application/json")
                httpURLConnection.doOutput = true

                // Convertendo o pedido em JSON
                val pedidoJson = JSONObject().apply {
                    put("mesa", pedido.mesa)
                    put("comanda", pedido.comanda)

                    val itensArray = JSONArray()
                    for (item in pedido.itensPedido) {
                        val itemJson = JSONObject().apply {
                            put("observacoes", item.observacoes)
                            put("idProduto", item.idProduto)
                        }
                        itensArray.put(itemJson)
                    }
                    put("itensPedido", itensArray)
                }

                // Enviando os dados
                val outputStreamWriter = OutputStreamWriter(httpURLConnection.outputStream)
                outputStreamWriter.write(pedidoJson.toString())
                outputStreamWriter.flush()

                // Verificando a resposta
                val responseCode = httpURLConnection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Pedido enviado com sucesso
                    runOnUiThread {
                        // Adicionar lógica de sucesso aqui
                    }
                } else {
                    // Tratamento de erro
                    runOnUiThread {
                        // Adicionar lógica de erro aqui
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    // Exibir mensagem de erro
                }
            }
        }
    }
}
