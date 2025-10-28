package com.example.radiosbuttom

import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
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
        var rg = findViewById<RadioGroup>(R.id.radio_grupo)
        var genero = "No Selecionado"
        var btnVerificar = findViewById<Button>(R.id.btnVerificar)

        rg.setOnCheckedChangeListener { group, checkedId ->
            genero = when (checkedId) {
                R.id.rbgM -> "Masculino"
                R.id.rbgF -> "Feminino"
                else -> "No Selecionado"
            }
        }
        btnVerificar.setOnClickListener {
            Toast.makeText(this, "Su género es $genero", Toast.LENGTH_SHORT).show()
        }

    }
}