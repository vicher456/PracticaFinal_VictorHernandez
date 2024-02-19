package com.example.practicafinal_victorhernandez

// InformacionActivity.kt
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InformacionActivity : AppCompatActivity() {

    // Declaración de variables
    private lateinit var textViewTitulo: TextView
    private lateinit var imageViewInformacion: ImageView
    private lateinit var textViewSinopsis: TextView
    private lateinit var buttonAtras: Button

    @SuppressLint("MissingInflatedId", "DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion)

        // Asignación de las varibles con sus respectivos ID del layout
        textViewTitulo = findViewById(R.id.textViewTitulo)
        imageViewInformacion = findViewById(R.id.imageViewInformacion)
        textViewSinopsis = findViewById(R.id.textViewSinopsis)
        buttonAtras = findViewById(R.id.buttonAtrasInformacion)

        // Botón para volver a la Activity de la ventana principal
        buttonAtras.setOnClickListener {
            val intent1 = Intent(this@InformacionActivity, PrincipalActivity::class.java)
            startActivity(intent1)
        }

        // Declaración de variables, que obtienen la selección y el tipo de la serie o película seleccionada
        val seleccion = intent.getStringExtra("seleccion")
        val tipo = intent.getStringExtra("tipo")

        // Obtiene la posición del elemento seleccionado
        val posicion = getPosition(seleccion, tipo)

        // Obtiene el tipo, en función de que sea serie o película, llama a un mapa o a otro, dependiendo de su posición
        val mapa = if (tipo == "serie") {
            Mapas().informacionSeries[posicion]
        } else {
            Mapas().informacionPeliculas[posicion]
        }

        // Se iguala el título que está en el mapa, al ID del TextView del layout
        textViewTitulo.text = mapa["titulo"]

        // Obtiene la imagen de la carpeta de "drawable", y en función de la serie o película que se haya seleccionado, lo obtiene de un mapa u otro
        val imagenId = resources.getIdentifier(mapa["imagen"], "drawable", packageName)

        // Si la imagen existe, lo iguala a su respectivo ID
        if (imagenId != 0) {
            imageViewInformacion.setImageResource(imagenId)
        }

        // Se iguala la sinopsis que está en el mapa, al ID del TextView del layout
        textViewSinopsis.text = mapa["sinopsis"]
    }

    // Función privada que obtiene un array u otro, en función de si se ha seleccionado una serie o una película
    private fun getPosition(seleccion: String?, tipo: String?): Int {
        return if (tipo == "serie") {
            resources.getStringArray(R.array.seleccionSeries).indexOf(seleccion)
        } else {
            resources.getStringArray(R.array.seleccionPeliculas).indexOf(seleccion)
        }
    }
}
