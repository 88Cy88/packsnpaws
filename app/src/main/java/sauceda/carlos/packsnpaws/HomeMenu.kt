package sauceda.carlos.packsnpaws

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import sauceda.carlos.packsnpaws.databinding.Activity3homeBinding
import sauceda.carlos.packsnpaws.databinding.Activity5usuarioProfileBinding

class HomeMenu : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: Activity3homeBinding
    private lateinit var bindingUsuario: Activity5usuarioProfileBinding
    private lateinit var storage: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = Activity3homeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        bindingUsuario = Activity5usuarioProfileBinding.inflate(layoutInflater)
        storage = FirebaseFirestore.getInstance()

        val inicioBoton = findViewById<ImageButton>(R.id.home_btn)
        inicioBoton.setOnClickListener {
            val intent = Intent(this, HomeMenu::class.java)
            startActivity(intent)
        }

        val petsBoton = findViewById<ImageButton>(R.id.pets_btn)
        petsBoton.setOnClickListener {
            val intent = Intent(this, PetProfile::class.java)
            startActivity(intent)
        }

        val perfilBoton = findViewById<ImageButton>(R.id.profile_btn)
        perfilBoton.setOnClickListener {
            val intent = Intent(this, UserProfile::class.java)
            startActivity(intent)
        }

        val chatsBoton = findViewById<ImageButton>(R.id.chats_btn)
        chatsBoton.setOnClickListener {
            val intent = Intent(this, ChatsProfile::class.java)
            startActivity(intent)
        }

        val mascotasCatalagoBoton = findViewById<ImageButton>(R.id.mascotaImagenDescipcion)
        mascotasCatalagoBoton.setOnClickListener {
            val intent = Intent(this, CatalagoMascotas::class.java)
            startActivity(intent)
        }


    }
}