package com.example.funciones

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var btn_ejecutar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        mapeo()
        btn_ejecutar.setOnClickListener {
            mensaje("Hola esta es la funcion")
            mensaje(suma(3,4))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    fun mapeo(){
        btn_ejecutar = findViewById(R.id.btn_ejecutar)
    }
    fun mensaje(texto: String){
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
    }

    fun suma (n1:Int, n2:Int):String {
        var r = n1 + n2
        var respuesta="La suma de $n1 y $n2 es: $r"
        return respuesta


    }
}