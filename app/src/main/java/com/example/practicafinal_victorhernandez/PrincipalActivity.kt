package com.example.practicafinal_victorhernandez

// PrincipalActivity.kt
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PrincipalActivity : AppCompatActivity() {

    // Declaración de variables
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private lateinit var bienvenidoUsuario: TextView
    private lateinit var imagenButtonPrincipal: ImageButton
    private lateinit var recyclerViewSeries: RecyclerView
    private lateinit var recyclerViewPeliculas: RecyclerView
    private lateinit var layoutSeries: LinearLayout
    private lateinit var layoutPeliculas: LinearLayout

    private lateinit var buttonSeries: Button
    private lateinit var buttonPeliculas: Button

    @SuppressLint("SetTextI18n", "MissingInflatedId", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        // Creación de las instancias de Firebase y Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Asignación de las varibles con sus respectivos ID del layout
        bienvenidoUsuario = findViewById(R.id.textViewUsuarioPrincipal)
        imagenButtonPrincipal = findViewById(R.id.imageButtonPerfilPrincipal)
        recyclerViewSeries = findViewById(R.id.recyclerViewSeries)
        recyclerViewPeliculas = findViewById(R.id.recyclerViewPeliculas)
        layoutSeries = findViewById(R.id.layoutSeries)
        layoutPeliculas = findViewById(R.id.layoutPeliculas)

        buttonSeries = findViewById(R.id.buttonSeries)
        buttonPeliculas = findViewById(R.id.buttonPeliculas)

        // Creación de varibale que se iguala al usuario que actualmente está logeado en la app
        val currentUserUid = auth.currentUser?.uid

        // Consultar datos del usuario en Firestore
        currentUserUid?.let { uid ->
            firestore.collection("usuarios").document(uid)
                .get()
                .addOnSuccessListener { document ->
                    // Si el documento existe, obtiene el usuario y la imagen de Firestore
                    if (document != null && document.exists()) {
                        val usuario = document.getString("usuario")
                        val imagenSeleccionadaIndex = document.getLong("imagenResourceId")?.toInt() ?: 0

                        // Aquí se muestra la información del TextView y el ImageView, que se obtuvieron anteriormente
                        imagenButtonPrincipal.setImageResource(imagenSeleccionadaIndex)
                        bienvenidoUsuario.text = usuario
                    }
                }

        }

        // Botón para hacer visible el layout de series, hacer invisible el de películas y que llame a la función para cargar las series
        buttonSeries.setOnClickListener {
            layoutSeries.visibility = View.VISIBLE
            layoutPeliculas.visibility = View.GONE
            cargarDatosSeries()
        }

        // Botón para hacer visible el layout de películas, hacer invisible el de series y que llame a la función para cargar las películas
        buttonPeliculas.setOnClickListener {
            layoutPeliculas.visibility = View.VISIBLE
            layoutSeries.visibility = View.GONE
            cargarDatosPeliculas()
        }

        // Botón que al darle te lleva a la Activity del perfil de usuario
        imagenButtonPrincipal.setOnClickListener {
            val intent1 = Intent(this@PrincipalActivity, PerfilActivity::class.java)
            startActivity(intent1)
        }
    }

    // Función privada para cargar los datos de las series
    private fun cargarDatosSeries() {
        val seriesArray = resources.getStringArray(R.array.seleccionSeries)

        val dataList = seriesArray.toList()

        val adapter = TextViewAdapterSeries(dataList) { item ->
            abrirDetalleActividad(item, "serie")
        }
        recyclerViewSeries.layoutManager = LinearLayoutManager(this)
        recyclerViewSeries.adapter = adapter
    }

    // Función privada para cargar los datos de las películas
    private fun cargarDatosPeliculas() {
        val peliculasArray = resources.getStringArray(R.array.seleccionPeliculas)

        val dataList = peliculasArray.toList()

        val adapter = TextViewAdapterPeliculas(dataList) { item ->
            abrirDetalleActividad(item, "pelicula")
        }
        recyclerViewPeliculas.layoutManager = LinearLayoutManager(this)
        recyclerViewPeliculas.adapter = adapter
    }

    // Función privada para obtener la serie o película seleccionada y que te mande a la Activity sobre su información
    private fun abrirDetalleActividad(item: String, tipo: String) {
        val intent = Intent(this@PrincipalActivity, InformacionActivity::class.java)
        intent.putExtra("seleccion", item) // Pasar el item (serie o película)
        intent.putExtra("tipo", tipo) // Pasar el tipo del elemento (serie o película)
        startActivity(intent)
    }
}