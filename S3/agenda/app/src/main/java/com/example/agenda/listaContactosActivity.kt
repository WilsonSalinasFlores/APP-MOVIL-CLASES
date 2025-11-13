package com.example.agenda

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

class listaContactosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lista_contactos)
        val codigo = intent.getStringExtra("codigo")?: ""

        obtenerDatosServicesPHP(codigo)


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
    fun obtenerDatosServicesPHP(codigoUsuario: String)
    {
        val url = "http://10.0.2.2:8080/wsagendacrud/datos/persona.php"
        val listaContactoes = findViewById<ListView>(R.id.lvwContactos)

        val items= arrayListOf<String>()
        val txtSaludo = findViewById<TextView>(R.id.txtSaludo)
        var datos= JSONObject()
        datos.put("codigo",codigoUsuario)
        datos.put("accion","contactos")


        val rq= Volley.newRequestQueue(this)

        val json= JsonObjectRequest(Request.Method.POST,url,datos,
            {
                    s->
                try {
                    val obj=(s)
                    if(obj.getBoolean("estado")){
                        val nombresUsuario = obj.getString("nombresUsuario")
                        val apellidosUsuario = obj.getString("apellidosUsuario")
                        txtSaludo.text ="¡Hola, $nombresUsuario $apellidosUsuario!"
                        val contactos = s.getJSONArray("listaContactos")
                        for (i in 0 until contactos.length()) {
                            val contacto = contactos.getJSONObject(i)
                            val nombres = contacto.getString("nombres")
                            val apellidos = contacto.getString("apellidos")
                            val telefono = contacto.getString("telefono")
                            val correo = contacto.getString("correo")
                            val texto = "$nombres $apellidos\nTel: $telefono\nCorreo: $correo"
                            items.add(texto)
                        }
                        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items)
                        listaContactoes.adapter = adapter


                    }else{
                        Toast.makeText(applicationContext,"No existen datos", Toast.LENGTH_SHORT).show()
                    }
                }catch (e: JSONException) {
                    Toast.makeText( applicationContext, e.message, Toast.LENGTH_SHORT).show()
                }
            },{ volleyError ->
                val message = volleyError?.message ?: "Error desconocido al conectar"
                Toast.makeText(applicationContext,message, Toast.LENGTH_SHORT).show()})
        rq.add(json)

    }
}