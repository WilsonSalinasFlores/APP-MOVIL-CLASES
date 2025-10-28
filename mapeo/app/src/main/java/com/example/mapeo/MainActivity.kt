package com.example.mapeo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        var txtUsuario= findViewById<EditText>(R.id.txt_usuario);
        var txtclave= findViewById<EditText>(R.id.txt_clave);
        var btnIngresar= findViewById<Button>(R.id.btn_ingresar);

        btnIngresar.setOnClickListener{
            val usu= txtUsuario.text.toString();
            val clave= txtclave.text.toString();
            var union = usu + " " + clave
            Toast.makeText(this, union, Toast.LENGTH_LONG).show()
        }

    }
}