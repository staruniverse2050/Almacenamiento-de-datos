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

            campoUsuario?.error = null
            campoPass?.error = null

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

            campoUsuario?.error = null
            campoPass?.error = null

        } else if(storedUser != user && storedPass != pass){
            campoUsuario?.error = "Usuario incorrecto"
            campoPass?.error = "Contraseña incorrecta"
            Toast.makeText(this, "El usuario o la contraseña ingresados no coinciden", Toast.LENGTH_SHORT).show()

    } else if (storedUser != user) {
            campoUsuario?.error = "Usuario incorrecto"
            Toast.makeText(
                this,
                "El usuario ingresado no coincide",
                Toast.LENGTH_SHORT
            ).show()
        }
        else {
            campoPass?.error = "Contraseña incorrecta"
            Toast.makeText(this, "La contraseña ingresada no coincide", Toast.LENGTH_SHORT).show()
        }
    }

}
