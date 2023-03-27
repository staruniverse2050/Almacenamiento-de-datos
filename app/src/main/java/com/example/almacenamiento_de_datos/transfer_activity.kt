package com.example.almacenamiento_de_datos

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class transfer_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer)
        val findleMessage = findViewById<TextView>(R.id.txtMessage)
        val nameUser: String? = intent.getStringExtra("nameUser")
        val passwordUser: String? = intent.getStringExtra("passwordUser")

        var data: Bundle? = this.intent.extras


        if (nameUser == "" && passwordUser == "") {
            findleMessage.text = "No existe la información correspondiente"
        }
        else if(data != null) {
            findleMessage.text = "Usuario guardado: ${data.getString("nameUser")} \nContraseña guardado: ${data.getString("passwordUser")}"
        }
            val btnExit:Button = findViewById(R.id.txtSalir)
            btnExit.setOnClickListener{onClick()}
        }


        private fun onClick() {
            finish()
        }
    }
