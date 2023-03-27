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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarComponentes()
        cargarDatos()

    }

    private fun iniciarComponentes() {

        var btnGuardar: Button = findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener { guardarDatos() }

        var btnCargar: Button = findViewById(R.id.btnCargar)
        btnCargar.setOnClickListener { cargarDatos() }

        campoUsuario = findViewById(R.id.campoUser)
        campoPass = findViewById(R.id.campoPass)
    }

    private fun guardarDatos() {

        var preferences: SharedPreferences =
            getSharedPreferences("credenciales", Context.MODE_PRIVATE)

        var usuario = campoUsuario?.text.toString()
        var pass = campoPass?.text.toString()

        var editor: SharedPreferences.Editor = preferences.edit()
        editor.putString("user", usuario)
        editor.putString("pass", pass)

        txtUsuario?.text = usuario
        txtPass?.text = pass

        editor.commit()

        Toast.makeText(this, "Se han registrado los datos", Toast.LENGTH_SHORT).show()
    }

    private fun cargarDatos() {
        var preferences: SharedPreferences =
            getSharedPreferences("credenciales", Context.MODE_PRIVATE)

        var storedUser: String? = preferences.getString("user", "")
        var storedPass: String? = preferences.getString("pass", "")

        var user = campoUsuario?.text.toString()
        var pass = campoPass?.text.toString()

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Bienvenido, ingrese usuario y contraseña", Toast.LENGTH_SHORT).show()
        } else if (storedUser == user && storedPass == pass) {
            txtUsuario?.text = storedUser
            txtPass?.text = storedPass
            Toast.makeText(this, "Credenciales válidas, sus datos se han cargado", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, transfer_activity::class.java)
            val content: Bundle = Bundle()
            content.putString("nameUser", user)
            content.putString("passwordUser", pass)
            intent.putExtras(content)
            startActivity(intent)
        } else {
            campoUsuario?.error = "Usuario o contraseña incorrectos"
            campoPass?.error = "Usuario o contraseña incorrectos"
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}
