package com.example.myapplication

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        var txtn1= findViewById<EditText>(R.id.txt_n1);
        var txtn2= findViewById<EditText>(R.id.txt_n2);
        var lbl_resultado= findViewById<TextView>(R.id.lbl_resultado);

        val n1 = txtn1.text.toString().toInt()
        val n2 = txtn2.text.toString().toInt()
        val suma = n1 + n2
        lbl_resultado.text = suma.toString()


    }
}