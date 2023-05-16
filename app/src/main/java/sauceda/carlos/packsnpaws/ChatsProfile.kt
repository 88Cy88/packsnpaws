package sauceda.carlos.packsnpaws

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ChatsProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_6chats_profile)

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

        val cahtsBoton = findViewById<ImageButton>(R.id.chats_btn)
        cahtsBoton.setOnClickListener {
            val intent = Intent(this, ChatsProfile::class.java)
            startActivity(intent)
        }



    }
}