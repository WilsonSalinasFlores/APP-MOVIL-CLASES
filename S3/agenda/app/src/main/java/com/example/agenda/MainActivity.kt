package com.example.agenda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var txtusu = findViewById<EditText>(R.id.txt_user)
        var txtcla = findViewById<EditText>(R.id.txt_cla)
        var btningresar= findViewById<Button>(R.id.btn_ingresar)
        var btnregistrasre= findViewById<Button>(R.id.btn_registrasre)

        btningresar.setOnClickListener {
            val url = "http://10.0.2.2:8080/wsagendacrud/datos/persona.php"
            var datos= JSONObject()
            datos.put("accion","login")
            datos.put("usuario",txtusu.text.toString())
            datos.put("clave",txtcla.text.toString())
            val rq= Volley.newRequestQueue(this)
            val json= JsonObjectRequest(Request.Method.POST,url,datos,{
                s->
                try {
                    val obj=(s)
                    if(obj.getBoolean("estado")){
                        val codigo=obj.getString("codigo")
                        val frm_lista = Intent(this, Contactos::class.java)
                        frm_lista.putExtra("cod_persona",codigo)
                        startActivity(frm_lista)
                    }else{
                        Toast.makeText(this,"Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    }
                }catch (e: JSONException) {
                    Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            },{ volleyError ->
                val message = volleyError?.message ?: "Error desconocido al conectar"
                Toast.makeText(this,message, Toast.LENGTH_SHORT).show()})
            rq.add(json)

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
