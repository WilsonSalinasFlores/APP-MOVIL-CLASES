package com.example.koltinfor

import android.os.Bundle
import android.widget.Button
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
        val btn_generar= findViewById<Button>(R.id.btn_generar)
        btn_generar.setOnClickListener {

            for(j in 1..10){
                println(j)

            }
            for(i in 0 until 10){
                println(i)
                Toast.makeText(this, i.toString(), Toast.LENGTH_SHORT).show()
            }
            for(n in 10 downTo 0 step 2){
                println(n)
                Toast.makeText(this, n.toString(), Toast.LENGTH_SHORT).show()
            }

        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}