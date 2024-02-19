package com.example.practicafinal_victorhernandez

// PerfilActivity.kt
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class PerfilActivity : AppCompatActivity() {

    // Declaración de variables
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private lateinit var imageViewImagenPerfil: ImageView
    private lateinit var textViewEmailPerfil: TextView
    private lateinit var textViewUsuarioPerfil: TextView
    private lateinit var textViewPasswordPerfil: TextView
    private lateinit var imageButtonOjoContrasena: ImageButton
    private lateinit var atrasButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        // Creación de las instancias de Firebase y Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        imageViewImagenPerfil = findViewById(R.id.imageViewPerfil)
        textViewEmailPerfil = findViewById(R.id.textViewEmail)
        textViewUsuarioPerfil = findViewById(R.id.textViewUsuario)
        textViewPasswordPerfil = findViewById(R.id.textViewContrasena)
        imageButtonOjoContrasena = findViewById(R.id.imageButtonOjoConContrasena)
        atrasButton = findViewById(R.id.buttonAtrasPerfil)

        // Creación de varibale que se iguala al usuario que actualmente está logeado en la app
        val currentUserUid = auth.currentUser?.uid

        // Consultar datos del usuario en Firestore
        currentUserUid?.let { uid ->
            firestore.collection("usuarios").document(uid)
                .get()
                .addOnSuccessListener { document ->
                    // Si el documento existe, obtiene el email, el usuario, la contraseña y la imagen de Firestore
                    if (document != null && document.exists()) {
                        val email = document.getString("email")
                        val usuario = document.getString("usuario")
                        val contrasena = document.getString("password")
                        val imagenSeleccionadaIndex = document.getLong("imagenResourceId")?.toInt() ?: 0

                        // Aquí se muestra la información de los TextView y el ImageView, que se obtuvieron anteriormente
                        // La única diferencia es que la contraseña se pasa oculta. Para que se muestre pulsar boton de ojo y se mostrará la contraseña
                        textViewEmailPerfil.text = email
                        textViewUsuarioPerfil.text = usuario
                        textViewPasswordPerfil.text = "*******"
                        imageViewImagenPerfil.setImageResource(imagenSeleccionadaIndex)

                        // Cambiar la visibilidad de la contraseña al hacer clic en el botón
                        imageButtonOjoContrasena.setOnClickListener {
                            if (textViewPasswordPerfil.text == "*******") {
                                textViewPasswordPerfil.text = contrasena
                                // Cambiar la imagen del botón a un ojo cerrado
                                imageButtonOjoContrasena.setImageResource(R.drawable.imagenojocontrasena)
                            } else {
                                textViewPasswordPerfil.text = "*******"
                                // Cambiar la imagen del botón a un ojo abierto
                                imageButtonOjoContrasena.setImageResource(R.drawable.imagenojocontrasena)
                            }
                        }
                    }
                }
        }

        // Botón para volver a la Activity de la ventana principal de la app
        atrasButton.setOnClickListener {
            val intent1 = Intent(this@PerfilActivity, PrincipalActivity::class.java)
            startActivity(intent1)
        }
    }
}