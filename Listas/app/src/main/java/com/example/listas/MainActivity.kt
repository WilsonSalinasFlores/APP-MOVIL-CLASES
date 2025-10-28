package com.example.listas

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
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
        val lista = findViewById<ListView>(R.id.lista)
        val ciudades = arrayOf("Quito","Guayaquil","Cuenca","Ambato","Manta","Esmeraldas","Puyo","Loja","Ibarra","Machala","Riobamba","Portoviejo","Santo Domingo","Latacunga","Tulcán","Tena","Macas","Quevedo","Otavalo","Salinas","Azogues","Nueva Loja","Babahoyo","Durán","Puerto Ayora")
        val ad= ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,ciudades)
        lista.adapter=ad
        lista.setOnItemClickListener { adapterView, view, position, id ->
            val item=lista.getItemAtPosition(position)
            Toast.makeText(applicationContext,item.toString(), Toast.LENGTH_LONG).show()
        }
    }
}