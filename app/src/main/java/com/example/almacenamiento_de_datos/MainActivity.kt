package com.example.almacenamiento_de_datos
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var campoUsuario: EditText? = null
    var campoPass: EditText? = null
    var txtUsuario: TextView? = null
    var txtPass: TextView? = null

    private fun iniciarComponentes() {
        var btnCargar:Button=findViewById(R.id.btnCargar)
        btnCargar.setOnClickListener { validarDatos() }

        campoUsuario=findViewById(R.id.campoUser)
        campoPass=findViewById(R.id.campoPass)
        txtUsuario=findViewById(R.id.txtUsuario)
        txtPass=findViewById(R.id.txtPass)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        iniciarComponentes()
        cargarDatos()

    }
    private fun validarDatos() {
        val usuario = campoUsuario?.text.toString()
        val pass = campoPass?.text.toString()

        if (usuario.isNotEmpty() && pass.isNotEmpty()) {
            cargarDatos(usuario, pass)
        } else {
            Toast.makeText(this, "Por favor, ingrese usuario y contraseña", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cargarDatos(user: String, pass: String) {
        var preferences: SharedPreferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE)
        var storedUser: String? =preferences.getString("user","No existe la información")
        var storedPass: String? =preferences.getString("pass","No existe la información")

        if (storedUser == user && storedPass == pass) {
            val intent = Intent(this, transfer_activity::class.java)
            val content: Bundle = Bundle()

            content.putString("nameUser", user)
            content.putString("passwordUser", pass)

            intent.putExtras(content)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }






}
