package com.example.variable

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val nombres = findViewById<EditText>(R.id.txtNombres)
        val apellidos = findViewById<EditText>(R.id.txtApellidos)
        val ingresar = findViewById<Button>(R.id.btnIngresar)
        ingresar.setOnClickListener {
            val f2 = Intent(this, formulario2::class.java)
            f2.putExtra("nom", nombres.text.toString())
            f2.putExtra("ape", apellidos.text.toString())
            startActivity(f2)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}