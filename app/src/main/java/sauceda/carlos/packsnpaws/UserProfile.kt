package sauceda.carlos.packsnpaws

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import sauceda.carlos.packsnpaws.databinding.Activity5usuarioProfileBinding
import java.util.*


class UserProfile  : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    private lateinit var bindingUsuario: Activity5usuarioProfileBinding

    var photo_user: ImageView? = null
    //referencias a Storage de firebase
    var storageReference: StorageReference? = null
    var storage_path = "humanos/*"
    private var image_url: Uri? = null
    private var imagenUsuarioUrl: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_5usuario_profile)
        bindingUsuario = Activity5usuarioProfileBinding.inflate(layoutInflater)
        setContentView(bindingUsuario.root)

        auth = FirebaseAuth.getInstance()
        storage = FirebaseFirestore.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        photo_user = findViewById(R.id.fotoUsuarioBtn)
        photo_user = bindingUsuario.fotoUsuarioBtn

        reload()
    //btn Editar
        val botonEditar = findViewById<ImageButton>(R.id.editbtn)
        botonEditar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Editar información")
            val nombreActual = bindingUsuario.nombreTextView.text.toString()
            val descripcionActual = bindingUsuario.descripcionET.text.toString()
            val view = layoutInflater.inflate(R.layout.editar_usuario, null)
            val etNombreUsuario = view.findViewById<EditText>(R.id.etNombreUsuario)
            val etDescripcion = view.findViewById<EditText>(R.id.etDescripcion)
            etNombreUsuario.setText(nombreActual)
            etDescripcion.setText(descripcionActual)

            builder.setView(view)
            builder.setPositiveButton("Guardar") { dialog, _ ->
                val nuevoNombre = etNombreUsuario.text.toString()
                val nuevaDescripcion = etDescripcion.text.toString()
                bindingUsuario.nombreTextView.text = nuevoNombre
                bindingUsuario.descripcionET.text = nuevaDescripcion

                val email = auth.currentUser?.email
                if (email != null) {
                    val documentRef = storage.document("usuarios/$email")
                    documentRef.update("nombre", nuevoNombre, "descripcionUsuario", nuevaDescripcion)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Los datos se actualizaron exitosamente en la base de datos
                                // Aquí puedes realizar acciones adicionales o mostrar un mensaje de éxito
                                Toast.makeText(this, "Datos actualizados exitosamente", Toast.LENGTH_SHORT).show()
                            } else {
                                // Ocurrió un error al actualizar los datos en la base de datos
                                // Aquí puedes mostrar un mensaje de error o realizar alguna otra acción de manejo de errores
                                Toast.makeText(this, "Error al actualizar los datos", Toast.LENGTH_SHORT).show()
                            }
                        }
                }

                dialog.dismiss()
            }

            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

            val dialog = builder.create()
            dialog.show()
        }

        //btn CAMBIAR IMAGEN
        val btnSeleccionarImagen = findViewById<ImageButton>(R.id.fotoUsuarioBtn)
        btnSeleccionarImagen.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, UserProfile.PICK_IMAGE_REQUEST)
        }

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

    private fun reload() {
        val email = auth.currentUser?.email // Obtener el email del usuario actualmente autenticado
        println("email: $email")
        if (email != null) {
            val documentRef = storage.document("usuarios/$email")
            println("documento: $documentRef")
            documentRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null && document.exists()) {
                        val nombre = document.getString("nombre")
                        val descripcionUsuario = document.getString("descripcionUsuario")
                        val fotoUsuario = document.getString("fotoUsuario")

                        println("nombre: $nombre")
                        println("descripcion: $descripcionUsuario")
                        println("fotoUsuario: $fotoUsuario")

                        runOnUiThread {
                            bindingUsuario.nombreTextView.text = nombre
                            bindingUsuario.descripcionET.text = descripcionUsuario

                            if (fotoUsuario != null) {
                                // Cargar la foto del usuario en el ImageButton
                                Picasso.with(this@UserProfile).load(fotoUsuario).fit().into(photo_user)
                            }
                        }
                    } else {
                        // El documento no existe o no se encontró
                        println("El documento no existe o no se encontró.")
                    }
                } else {
                    // Ocurrió un error al obtener el documento
                    println("Error al obtener el documento: ${task.exception}")
                }
            }
        }
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }
    //Guarda y Actualiza la imagen
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            val selectedImageUri = data.data

            // Obtener el nombre del archivo de imagen
            val imageFileName = UUID.randomUUID().toString()

            // Crear una referencia al archivo en Firebase Storage
            val imageRef = storageReference?.child("humanos/$imageFileName")

            // Subir la imagen a Firebase Storage
            imageRef?.putFile(selectedImageUri!!)
                ?.addOnSuccessListener { taskSnapshot ->
                    // Obtener la URL de descarga de la imagen subida
                    imageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                        // Actualizar la foto del usuario en la base de datos
                        val email = auth.currentUser?.email

                        if (email != null) {
                            val usuarioActualRef = storage.collection("usuarios").document(email)
                            usuarioActualRef.update("fotoUsuario", downloadUrl.toString())
                                .addOnSuccessListener {
                                    // Actualizar la URL de la imagen del usuario
                                    imagenUsuarioUrl = downloadUrl.toString()


                                    // Cargar la foto subida en el ImageButton
                                    runOnUiThread {
                                        Picasso.with(this).load(imagenUsuarioUrl).fit().into(photo_user)
                                        reload()
                                    }

                                    Toast.makeText(this, "Imagen subida exitosamente", Toast.LENGTH_SHORT).show()
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(this, "Error al subir la imagen", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                }
                ?.addOnFailureListener { e ->
                    Toast.makeText(this, "Error al subir la imagen", Toast.LENGTH_SHORT).show()
                }
        }
    }


}