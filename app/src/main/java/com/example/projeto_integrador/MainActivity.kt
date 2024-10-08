package com.example.projeto_integrador

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var usuarioInput : EditText
    lateinit var senhaInput : EditText
    lateinit var loginBtn : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usuarioInput = findViewById(R.id.usuario)
        senhaInput = findViewById(R.id.senha)
        loginBtn = findViewById(R.id.login_btn)

        loginBtn.setOnClickListener {
            val username = usuarioInput.text.toString()
            val senha = senhaInput.text.toString()
            Log.i("Log das credenciais", "Username : $username e senha $senha")
        }
    }
}