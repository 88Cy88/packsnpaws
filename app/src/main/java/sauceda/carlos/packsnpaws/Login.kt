package sauceda.carlos.packsnpaws

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import sauceda.carlos.packsnpaws.databinding.Activity2loginBinding

class Login:AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: Activity2loginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2loginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val atrasBoton = findViewById<ImageButton>(R.id.back_btn)
        atrasBoton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val registerBoton = findViewById<Button>(R.id.register_btn)
        registerBoton.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        val olvidarBoton = findViewById<Button>(R.id.forget_password)
        olvidarBoton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Inicializamos
        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.iniciarBtn.setOnClickListener {
            val mEmail=binding.emailEditText.text.toString()

            val mPassword=binding.passwordEditText.text.toString()

            if (mEmail.isEmpty()) {
                Log.d("TAG", "El texto está vacío")
            } else {
                Log.d("TAG", "El texto contiene: $mEmail")
            }

            if (mPassword.isEmpty()) {
                Log.d("TAG", "El texto está vacío")
            } else {
                Log.d("TAG", "El texto contiene: $mPassword")
            }

            when{
                mEmail.isEmpty() || mPassword.isEmpty() ->{
                    Toast.makeText(baseContext, "Email o contrasena incorrecta", Toast.LENGTH_SHORT).show()

                } else ->{
                signIn(mEmail,mPassword)
            }
            }
        }


    }

    private fun signIn(email:String,password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ContentValues.TAG, "signInWithEmail:success")
                    reload()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    private fun reload(){
        val intent= Intent(this,HomeMenu::class.java)
        this.startActivity(intent)
    }


}