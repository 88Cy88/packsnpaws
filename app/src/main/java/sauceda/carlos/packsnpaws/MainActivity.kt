package sauceda.carlos.packsnpaws

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_0comienzo)

        val loginButton = findViewById<Button>(R.id.login_btn)

        loginButton.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        val registerBoton = findViewById<Button>(R.id.register_btn)

        registerBoton.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }



    }
}