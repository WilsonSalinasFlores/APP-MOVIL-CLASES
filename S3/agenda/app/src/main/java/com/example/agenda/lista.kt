package com.example.agenda

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
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
import org.json.JSONException as JsonException


class lista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista)

        val codigo = intent.getStringExtra("codigo")?: "0"

        cargarAgenda(codigo)


        val btnregresar = findViewById<Button>(R.id.btn_regresar)

        btnregresar.setOnClickListener {
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun cargarAgenda(codigo: String)
    {
        val lista = findViewById<ListView>(R.id.lvi_listado)
        //obtener desde la base de datos
        val items= arrayListOf<String>()
        val txtbienvenido = findViewById<TextView>(R.id.txt_bienvenido)
        val url = "http://10.0.2.2:8080/WSAGENDA/datos/persona.php"
        var datos= JSONObject()
        datos.put("accion","lista")
        datos.put("codigo",codigo.toString())

        val rq= Volley.newRequestQueue(this)
        val json= JsonObjectRequest(Request.Method.POST,url,datos,{
                s->
            try {
                val obj=(s)
                if(obj.getBoolean("estado")){
                    txtbienvenido.text ="Bienvenido: " + obj.getJSONObject("usuario").getString("nombres")+
                            " " + obj.getJSONObject("usuario").getString("apellidos")
                    val contactosArray = s.getJSONArray("contactos")

                    // 3. Recorrer el array de contactos
                    for (i in 0 until contactosArray.length()) {
                        val contacto = contactosArray.getJSONObject(i)
                        val nombres = contacto.getString("nombres")
                        val apellidos = contacto.getString("apellidos")
                        val telefono = contacto.getString("telefono")
                        val correo = contacto.getString("correo")
                        val itemFormateado = "$nombres $apellidos\nTel: $telefono | Correo: $correo"
                        items.add(itemFormateado)
                    }
                    val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
                    lista.adapter = adapter


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
}