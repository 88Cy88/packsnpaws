package sauceda.carlos.packsnpaws

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import sauceda.carlos.packsnpaws.databinding.Activity1registerBinding
import sauceda.carlos.packsnpaws.databinding.Activity2loginBinding

class Register : AppCompatActivity() {
    private lateinit var binding: Activity1registerBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_1register)
        binding = Activity1registerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        storage = FirebaseFirestore.getInstance()

        val atrasBoton = findViewById<ImageButton>(R.id.back_btn)
        atrasBoton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Inicializamos
        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener {
            val nom = binding.nombreTXT.text.toString()
            val email = binding.emailTXT.text.toString()
            val pass = binding.passwordTXT.text.toString()
            val tiempo = binding.fechaTXT.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && pass.isNotEmpty() && tiempo.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val mascotasList = arrayListOf<HashMap<String, String>>()
                        storage.collection("usuarios").document(email).set(
                            hashMapOf(
                                "email" to email,
                                "nombre" to nom,
                                "contraseña" to pass,
                                "cumpleaños" to tiempo,
                                "Mascotas" to mascotasList
                            )
                        ).addOnSuccessListener { documentReference ->
                                Toast.makeText(this, "Se a creado el usuario", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "error al agregar el usuario a la base de datos", Toast.LENGTH_SHORT).show()
                            }

                        val intent = Intent(this, Login::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "El formato de correo electrónico es incorrecto", Toast.LENGTH_SHORT).show()
                    }
                }

            }else{
                Toast.makeText(this, "Los campos vacios no son permitidos !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}