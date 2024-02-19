package com.example.practicafinal_victorhernandez

// LoginActivity.kt
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    // Declaración de variables
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private lateinit var imagenImageView: ImageView
    private lateinit var emailEditText: EditText
    private lateinit var contrasenaEditText: EditText
    private lateinit var registrarButton: Button
    private lateinit var iniciarSesionButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Creación de las instancias de Firebase y Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Asignación de las varibles con sus respectivos ID del layout
        imagenImageView = findViewById(R.id.imageViewPerfilLogin)
        emailEditText = findViewById(R.id.editTextPerfilEmail)
        contrasenaEditText = findViewById(R.id.editTextContrasena)
        registrarButton = findViewById(R.id.buttonRegistrar)
        iniciarSesionButton = findViewById(R.id.buttonInicioSesion)

        // Recogida de la imagen que se ha escogido en la Activity de registro y se iguala a su ImageView correspondiente
        val imagenSeleccionadaIndex = intent.getIntExtra("imagen_seleccionada_index", 0)
        val resourceId = obtenerResourceIdSeleccionado(imagenSeleccionadaIndex)
        imagenImageView.setImageResource(resourceId)

        // Botón para registrar el usuario, si aún no se ha hecho o se quiere regitrar otro
        registrarButton.setOnClickListener {
            val intent1 = Intent(this@LoginActivity, RegistroActivity::class.java)
            startActivity(intent1)
        }

        // Botón para iniciar sesión con el usuario seleccionado
        iniciarSesionButton.setOnClickListener {
            // Declaración de variables, que se igualan a los diferentes EditText de la Activity
            val email = emailEditText.text.toString().trim()
            val password = contrasenaEditText.text.toString().trim()

            // Comprobación de que el E-mail y la contraseña no estén vacíos
            // Si lo están, se manda un mensaje de error al usuario
            if (email.isBlank() || password.isBlank()) {
                showToast("Credenciales vacías: el usuario y/o la contraseña no pueden estar vacíos")
                return@setOnClickListener
            }

            // Si no hay ningún error, se empieza a ejecutar el método para iniciar sesión con el usuario seleccionado
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    // Si el usuario está correcto, inicia seión con el usuario registrado
                    // Se manda un mensaje verificando que el usuario ha inciado sesión y pasa a la siguiente Activity
                    if (task.isSuccessful) {
                        showToast("Se ha iniciado sesión correctamente")

                        // También obtiene la colección de usuarios mediante Firestore, para iniciar sesión con el usuario resgistrado
                        firestore.collection("usuarios").document(auth.currentUser!!.uid)
                            .get()
                            .addOnSuccessListener { document ->
                                // Comprueba si el documento existe, si existe obtiene la imagen seleccionada en el registro y la iguala al ImageView de la Activity
                                if (document != null && document.exists()) {
                                    val imagenSeleccionadaIndex = document.getLong("imagenResourceId")?.toInt() ?: 0
                                    imagenImageView.setImageResource(imagenSeleccionadaIndex)
                                }
                                // Obtiene el usuario que estaba en Firestore y pasa a la siguiente Activity
                                val usuario = document.getString("usuario")
                                val intent = Intent(this@LoginActivity, PrincipalActivity::class.java)
                                intent.putExtra("Usuario", usuario)
                                startActivity(intent)
                            }
                            // Si el inicio de sesión falla, se lanza un mensaje de error con una excepción
                            .addOnFailureListener { e ->
                                showToast("Error al obtener datos de Firestore: ${e.message}")
                            }
                    // Comprobación de que la contraseña sea igual a la del usuario que se habia registrado
                    // También se comprueba que el usuario sea correcto y exista
                    // Si lo están, se manda un mensaje de error al usuario
                    } else if (!email.equals(auth.currentUser) || !password.equals(auth.currentUser)) {
                        showToast("Usuario no existe en la base de datos o la contraseña es incorrecta")
                    }
                }
        }
    }

    // Función privada para mostrar mensajes, ya sean de error o para mandar otro tipo de mensajes
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Función privada para obtener el ID de las imágenes, cuando se selcciona con el adaptador creado anteriormente
    private fun obtenerResourceIdSeleccionado(position: Int): Int {
        return when (position) {
            1 -> R.drawable.imagenperfil1
            2 -> R.drawable.imagenperfil2
            3 -> R.drawable.imagenperfil3
            4 -> R.drawable.imagenperfil4
            5 -> R.drawable.imagenperfil5
            else -> R.drawable.imagenpredeterminada
        }
    }
}
