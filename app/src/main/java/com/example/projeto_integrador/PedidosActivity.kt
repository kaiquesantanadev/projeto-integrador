package com.example.projeto_integrador

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projeto_integrador.PaginatedResponse
import com.example.projeto_integrador.Pedido
import com.example.projeto_integrador.PedidoAdapter
import com.example.projeto_integrador.R
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class PedidosActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pedidoAdapter: PedidoAdapter
    private val pedidos = mutableListOf<Pedido>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchPedidos()
    }

    private fun fetchPedidos() {
        Thread {
            try {
                val url = URL("http://10.0.2.2:8080/pedido") // Use 10.0.2.2 para emulador
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val inputStream = connection.inputStream
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    reader.close()

                    val gson = Gson()
                    val paginatedResponse = gson.fromJson(response.toString(), PaginatedResponse::class.java)
                    pedidos.addAll(paginatedResponse.content)

                    runOnUiThread {
                        pedidoAdapter = PedidoAdapter(pedidos)
                        recyclerView.adapter = pedidoAdapter
                    }
                } else {
                    Log.e("HTTP", "Erro na resposta: $responseCode")
                }
            } catch (e: Exception) {
                Log.e("HTTP", "Erro: ${e.message}")
            }
        }.start()
    }
}
