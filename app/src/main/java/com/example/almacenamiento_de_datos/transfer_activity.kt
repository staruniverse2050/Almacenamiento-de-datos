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

        var data: Bundle? = this.intent.extras


         if(data != null) {
            findleMessage.text = "Usuario: ${data.getString("nameUser")} \nContraseña: ${data.getString("passwordUser")}"
        }
            val btnExit:Button = findViewById(R.id.txtSalir)
            btnExit.setOnClickListener{onClick()}
        }

        private fun onClick() {
            finish()
        }
    }
