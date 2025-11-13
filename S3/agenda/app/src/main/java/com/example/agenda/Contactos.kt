package com.example.agenda

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
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


class Contactos : AppCompatActivity() {

    lateinit var txtcodigo: EditText
    lateinit var txtnombre: EditText
    lateinit var txtapellido: EditText
    lateinit var txttelefono: EditText
    lateinit var txtcorreo: EditText
    lateinit var btnnuevo: Button
    lateinit var btnguardar: Button
    lateinit var btncancelar: Button
    lateinit var btneliminar: Button
    lateinit var btnactualizar: Button
    lateinit var lista: ListView

    val codigos= ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contactos)

        mapeo()

        txtcodigo.setText(intent.getStringExtra("cod_persona"))
        lcajas()
        bcajas()
        bbotones()
        btnnuevo.isEnabled=true
        consultar()
        btnnuevo.setOnClickListener {
            bcajas()
            btncancelar.isEnabled=true
            btnguardar.isEnabled=true
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun mapeo()
    {
        txtcodigo= findViewById(R.id.txt_codigo)
        txtnombre= findViewById(R.id.txt_nombre)
        txtapellido = findViewById(R.id.txt_apellidos)
        txttelefono = findViewById(R.id.txt_telefono)
        txtcorreo = findViewById(R.id.txt_correo)

        btnnuevo = findViewById(R.id.btn_nuevo)
        btnguardar = findViewById(R.id.btn_guardar)
        btncancelar = findViewById(R.id.btn_cancelar)
        btneliminar = findViewById(R.id.btn_eliminar)
        btnactualizar = findViewById(R.id.btn_actualizar)
        lista = findViewById(R.id.lvl_contactos)


    }
    fun lcajas(){
        txtapellido.setText("")
        txttelefono.setText("")
        txtcorreo.setText("")
        txtnombre.setText("")
    }
    fun bcajas(){
        txtapellido.isEnabled=false
        txttelefono.isEnabled=false
        txtcorreo.isEnabled=false
        txtnombre.isEnabled=false

    }
    fun acajas(){
        txtapellido.isEnabled=true
        txttelefono.isEnabled=true
        txtcorreo.isEnabled=true
        txtnombre.isEnabled=true
    }
    fun bbotones(){
        btnguardar.isEnabled=false
        btncancelar.isEnabled=false
        btneliminar.isEnabled=false
        btnactualizar.isEnabled=false
    }
    fun abotones(){
        btnguardar.isEnabled=true
        btncancelar.isEnabled=true
        btneliminar.isEnabled=true
        btnactualizar.isEnabled=true
    }


    fun consultar(){
        val url = "http://10.0.2.2:8080/wsagendacrud/datos/contacto.php"
        val al = ArrayList<String>()
        var datos= JSONObject()
        datos.put("accion","consultar")
        datos.put("cod_persona",txtcodigo.text)
        val rq= Volley.newRequestQueue(this)
        val json= JsonObjectRequest(Request.Method.POST,url,datos,{
                s->
            try {
                val obj=(s)
                if(obj.getBoolean("estado")){
                    val array = obj.getJSONArray("datos")
                    for(i in 0  .. array.length()-1) {
                        val fila = array.getJSONObject(i)
                        codigos.add(fila.getString("codigo"))
                        al.add(fila.getString("nombre") +" "+ fila .getString("apellido") )

                    }
                    val ad = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al)
                    lista.adapter = ad
                    ad.notifyDataSetChanged()
                }else{
                    Toast.makeText(this,"No Existen contactos", Toast.LENGTH_SHORT).show()
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