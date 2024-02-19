package com.example.practicafinal_victorhernandez

// RegistroActivity.kt
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistroActivity : AppCompatActivity() {

    // Declaración de variables
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    private lateinit var imagenImageView: ImageView
    private lateinit var selectorImagenPerfilSpinner: Spinner
    private lateinit var emailEditText: EditText
    private lateinit var usuarioEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var cancelarButton: Button
    private lateinit var confirmarButton: Button

    private var imagenSeleccionadaIndex: Int = 0

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Creación de las instancias de Firebase y Firestore
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        // Asignación de las varibles con sus respectivos ID del layout
        imagenImageView = findViewById(R.id.imageButtonPerfilRegistro)
        selectorImagenPerfilSpinner = findViewById(R.id.spinnerSelectorImagenPerfil)
        emailEditText = findViewById(R.id.editTextEmail)
        usuarioEditText = findViewById(R.id.editTextUsuarioRegistro)
        passwordEditText = findViewById(R.id.editTextContrasenaRegistro)
        cancelarButton = findViewById(R.id.buttonCancelar)
        confirmarButton = findViewById(R.id.buttonConfirmar)

        // Creación del Adapter para la selección de imágenes del perfil
        selectorImagenPerfilSpinner.adapter = ArrayAdapter.createFromResource(this, R.array.seleccionImagenesPerfil, android.R.layout.simple_spinner_item)

        // Función que al hacer clic en una opción se seleccione la imagen y se cambie la imagen predeterminada por la que se ha elegido
        selectorImagenPerfilSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                imagenSeleccionadaIndex = position
                imagenImageView.setImageResource(obtenerResourceIdSeleccionado(position))
            }

            // Si no se selecciona ninguna imagen, se mandará un mensaje al usuario de que debe de poner si o si, la imagen de perfil
            // Aparte, no dejará avanzar a la siguiente Activity, si no selecciona nada
            override fun onNothingSelected(parent: AdapterView<*>?) {
                confirmarButton.isEnabled = false
                showToast("Selecciona una imagen de perfil para registrar el usuario.")
            }
        }

        // Botón para confirmar el registro del usuario
        confirmarButton.setOnClickListener {
            // Declaración de variables, que se igualan a los diferentes EditText de la Activity
            // Y la imagen, se iguala al adaptador que creamos anteriormente
            val usuario = usuarioEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val imagenResourceId = obtenerResourceIdSeleccionado(selectorImagenPerfilSpinner.selectedItemPosition)

            // Comprobación de que el usuario, la contraseña, el E-mail y la imagen no estén vacíos
            // Si lo están, se manda un mensaje de error al usuario
            if (usuario.isBlank() || password.isBlank() || email.isBlank() || imagenResourceId == 0) {
                showToast("El usuario, la contraseña, el mail y la imagen del perfil no pueden estar vacíos.")

            // Comprobación de que la contraseña tenga más de 6 caracteres
            // Si lo están, se manda un mensaje de error al usuario
            }else if (password.length < 6) {
                showToast("Error de autentificación: Contraseña Demasiado Corta")

            // Comprobación de que el E-mail cumpla con el formato válido
            // Si lo están, se manda un mensaje de error al usuario
            }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showToast("Error de autentificación: El E-mail no cumple el formato válido")
            }else
                // Si no hay ningún error, se empieza a ejecutar el método para registrar al usuario
                auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    // Si el usuario está correcto, crea un usuario y pasa información a las siguientes Activitiys
                    // La información contiene el usuario, el E-amil, la contraseña y la imagen seleccionados durante el registro
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        user?.let {
                            val userData = hashMapOf(
                                "usuario" to usuario,
                                "email" to email,
                                "password" to password,
                                "imagenResourceId" to imagenResourceId
                            )
                            // También se crea una colección de usuarios mediante Firestore, para ir guardando todos los usuarios que se vayan registrando
                            firestore.collection("usuarios").document(user.uid)
                                .set(userData)
                                .addOnSuccessListener {
                                    // Se manda un mensaje verificando que el usuario se ha registrado y pasa a la siguiente Activity
                                    showToast("Registro exitoso. Datos guardados en Firestore.")
                                    val intent = Intent(this@RegistroActivity, LoginActivity::class.java)
                                    startActivity(intent)
                                }
                                // Si falla, se muestra un error al usuario
                                .addOnFailureListener {
                                    showToast("Error al guardar datos en Firestore")
                                }
                        }
                    } else {
                        // Si el task, no es correcto, manda un error al usuario
                        showToast("Error al registrar usuario en Firebase: USUARIO YA EXISTENTE")
                    }
                }
        }

        // Botón para cancelar el registro del usuario y volver a la Activity de login
        cancelarButton.setOnClickListener {
            val intent1 = Intent(this@RegistroActivity, LoginActivity::class.java)
            startActivity(intent1)
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