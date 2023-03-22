package com.example.almacenamiento_de_datos
import android.content.Context
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    var campoUsuario:EditText?=null
    var campoPass:EditText?=null
    var txtUsuario:TextView?=null
    var txtPass:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarComponentes()
        cargarDatos()

    }

    private fun iniciarComponentes() {

        var btnGuardar:Button=findViewById(R.id.btnGuardar)
        btnGuardar.setOnClickListener { guardarDatos() }

        var btnCargar:Button=findViewById(R.id.btnCargar)
        btnCargar.setOnClickListener { cargarDatos() }

        campoUsuario=findViewById(R.id.campoUser)
        campoPass=findViewById(R.id.campoPass)
        txtUsuario=findViewById(R.id.txtUsuario)
        txtPass=findViewById(R.id.txtPass)

    }

    private fun guardarDatos() {

        var preferences:SharedPreferences=getSharedPreferences("credenciales",Context.MODE_PRIVATE)

        var usuario= campoUsuario?.text.toString()
        var pass= campoPass?.text.toString()

        var editor:SharedPreferences.Editor=preferences.edit()
        editor.putString("user",usuario)
        editor.putString("pass",pass)

        txtUsuario?.text=usuario
        txtPass?.text=pass

        editor.commit()

        Toast.makeText(this,"Se han registrado los datos",Toast.LENGTH_SHORT).show()
    }

    private fun cargarDatos() {
        var preferences:SharedPreferences=getSharedPreferences("credenciales",Context.MODE_PRIVATE)

        var user: String? =preferences.getString("user","No existe la información")
        var pass: String? =preferences.getString("pass","No existe la información")

        txtUsuario?.text=user
        txtPass?.text=pass

    }
}
