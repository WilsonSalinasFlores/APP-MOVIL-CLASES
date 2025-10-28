package com.example.checkbox

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var chkPapas= findViewById<CheckBox>(R.id.chkPapas)
        var chkQueso= findViewById<CheckBox>(R.id.chkQueso)
        var btnConfirmar = findViewById<Button>(R.id.btnConfirmar)
        btnConfirmar.setOnClickListener {
            var seleccion = ""
            if (chkPapas.isChecked) {
                seleccion += "Papas Extras "
            }
            if (chkQueso.isChecked) {
                seleccion += "Queso Extra"
            }
            if (seleccion.isEmpty()) {
                Toast.makeText(applicationContext, "No ha seleccionado nada", Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(applicationContext, "Ha seleccionado $seleccion", Toast.LENGTH_SHORT).show()
            }
        }

    }
}