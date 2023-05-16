package sauceda.carlos.packsnpaws

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.graphics.Bitmap
import android.net.Uri
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import sauceda.carlos.packsnpaws.databinding.Activity4petProfileBinding

import java.util.*


class PetProfile : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    private lateinit var bindingPet: Activity4petProfileBinding

    var photo_pet: ImageView? = null
    //referencias a Storage de firebase
    var storageReference: StorageReference? = null
    var storage_path = "animales/*"
    private var image_url: Uri? = null
    private var imagenMascotaUrl: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_4pet_profile)
        bindingPet = Activity4petProfileBinding.inflate(layoutInflater)
        setContentView(bindingPet.root)

        auth = FirebaseAuth.getInstance()
        storage = FirebaseFirestore.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        photo_pet = findViewById(R.id.fotoMascotaBtn)
        photo_pet = bindingPet.fotoMascotaBtn
        reload()
        cargarList()


//btn AGREGAR MASCOTA
        val AgregarPet = findViewById<ImageButton>(R.id.btn_añadirNomMascota)
        AgregarPet.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Agregar Mascota")
            val view = layoutInflater.inflate(R.layout.agregar_mascota, null)
            val etNombrePet = view.findViewById<EditText>(R.id.etNombrePet)
            val etDescripcion = view.findViewById<EditText>(R.id.etDescripcion)
            builder.setView(view)

            builder.setPositiveButton("Guardar") { dialog, _ ->
                val nombreMascota = etNombrePet.text.toString()
                val descripcionMascota = etDescripcion.text.toString()
                println(nombreMascota)
                println(descripcionMascota)

                val email = auth.currentUser?.email
                if (email != null) {

                    if (nombreMascota.isNotEmpty() && descripcionMascota.isNotEmpty()) {
                        // Crear el objeto HashMap para la nueva mascota
                        val nuevaMascota = hashMapOf(
                            "nombreMascota" to nombreMascota,
                            "descripcionMascota" to descripcionMascota
                        )

                        // Obtener la referencia del documento del usuario actual
                        val usuarioActualRef = storage.collection("usuarios").document(email)

                        // Actualizar el campo "Mascotas" del documento del usuario actual
                        usuarioActualRef.get().addOnSuccessListener { document ->
                            if (document != null && document.exists()) {
                                val mascotasList = document.get("Mascotas") as ArrayList<HashMap<String, String>>?
                                mascotasList?.add(nuevaMascota)
                                reload()
                                // Actualizar el campo "Mascotas" en la base de datos
                                cargarList()
                                usuarioActualRef.update("Mascotas", mascotasList)
                                    .addOnSuccessListener {
                                        Toast.makeText(this, "Mascota agregada exitosamente", Toast.LENGTH_SHORT).show()
                                    }
                                    .addOnFailureListener { e ->
                                        Toast.makeText(this, "Error al agregar la mascota a la base de datos", Toast.LENGTH_SHORT).show()
                                    }
                            }
                        }
                    } else {
                        Toast.makeText(this, "Los campos vacíos no son permitidos", Toast.LENGTH_SHORT).show()
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
//btn EDITAR MASCOTA
        val botonEditar = findViewById<ImageButton>(R.id.editbtn)
        botonEditar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Editar Mascota")
            val view = layoutInflater.inflate(R.layout.agregar_mascota, null)
            val etNombrePet = view.findViewById<EditText>(R.id.etNombrePet)
            val etDescripcion = view.findViewById<EditText>(R.id.etDescripcion)
            builder.setView(view)

            // Obtener el nombre y la descripción de la mascota actual
            val nombreActual = bindingPet.mascotaTXT.text.toString()
            val descripcionActual = bindingPet.descripcionTXT.text.toString()
            etNombrePet.setText(nombreActual)
            etDescripcion.setText(descripcionActual)

            builder.setPositiveButton("Guardar") { dialog, _ ->
                val nuevoNombreMascota = etNombrePet.text.toString()
                val nuevaDescripcionMascota = etDescripcion.text.toString()

                val email = auth.currentUser?.email
                if (email != null) {
                    if (nuevoNombreMascota.isNotEmpty() && nuevaDescripcionMascota.isNotEmpty()) {
                        val usuarioActualRef = storage.collection("usuarios").document(email)
                        usuarioActualRef.get().addOnSuccessListener { document ->
                            if (document != null && document.exists()) {
                                val mascotasList = document.get("Mascotas") as ArrayList<HashMap<String, String>>?
                                if (mascotasList != null) {
                                    // Encontrar la mascota actual en la lista y actualizar sus valores
                                    for (mascota in mascotasList) {
                                        val nombreMascota = mascota["nombreMascota"]
                                        if (nombreMascota == nombreActual) {
                                            mascota["nombreMascota"] = nuevoNombreMascota
                                            mascota["descripcionMascota"] = nuevaDescripcionMascota
                                            break
                                        }
                                    }

                                    // Actualizar el campo "Mascotas" en la base de datos
                                    usuarioActualRef.update("Mascotas", mascotasList)
                                        .addOnSuccessListener {
                                            // Actualizar los textos con los valores modificados
                                            runOnUiThread {
                                                bindingPet.mascotaTXT.text = nuevoNombreMascota
                                                bindingPet.descripcionTXT.text = nuevaDescripcionMascota
                                                cargarList()
                                            }
                                            Toast.makeText(this, "Mascota editada exitosamente", Toast.LENGTH_SHORT).show()
                                        }
                                        .addOnFailureListener { e ->
                                            Toast.makeText(this, "Error al editar la mascota en la base de datos", Toast.LENGTH_SHORT).show()
                                        }
                                }
                            }
                        }
                    } else {
                        Toast.makeText(this, "Los campos vacíos no son permitidos", Toast.LENGTH_SHORT).show()
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
        val btnSeleccionarImagen = findViewById<ImageButton>(R.id.fotoMascotaBtn)
        btnSeleccionarImagen.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
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
    //metodo para recargar la informacion de la mascota
    private fun reload(){
        val email = auth.currentUser?.email // Obtener el email del usuario actualmente autenticado
        println("email: $email")
        if (email != null) {
            val documentRef = storage.document("usuarios/$email")
            println("documento: $documentRef")
            documentRef.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null && document.exists()) {
                        val mascotasList = document.get("Mascotas") as ArrayList<HashMap<String, String>>?
                        if (!mascotasList.isNullOrEmpty()) {
                            val primeraMascota = mascotasList[0]
                            val nombre = primeraMascota["nombreMascota"]
                            val descripcionMascota = primeraMascota["descripcionMascota"]
                            val fotoMascota = primeraMascota["fotoMascota"]
                            println(nombre)
                            println(descripcionMascota)
                            if (nombre != null) {
                                runOnUiThread {
                                    bindingPet.mascotaTXT.text = nombre
                                }
                            }
                            if (descripcionMascota != null) {
                                runOnUiThread {
                                    bindingPet.descripcionTXT.text = descripcionMascota
                                }
                            }
                            if (fotoMascota != null) {
                                runOnUiThread {
                                    Picasso.with(this).load(fotoMascota).fit().into(bindingPet.fotoMascotaBtn)
                                }
                            } else {
                                // Si la mascota seleccionada no tiene imagen, mostrar una imagen predeterminada
                                runOnUiThread {
                                    bindingPet.fotoMascotaBtn.setImageResource(R.drawable.patita)
                                }
                            }
                        } else {
                            // La lista de mascotas está vacía
                            println("La lista de mascotas está vacía.")
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

    //metodo para cargar la lista de mascotas
    private fun cargarList() {
        val email = auth.currentUser?.email // Obtener el email del usuario actualmente autenticado
        val documentRef = storage.document("usuarios/$email")
        val listView = bindingPet.ListMascotasImages

        documentRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null && document.exists()) {
                    val mascotasList = document.get("Mascotas") as ArrayList<HashMap<String, String>>?
                    val nombresMascotas = ArrayList<String>()

                    if (mascotasList != null) {
                        for (mascota in mascotasList) {
                            val nombreMascota = mascota["nombreMascota"]
                            if (nombreMascota != null) {
                                nombresMascotas.add(nombreMascota)
                            }
                        }
                    }

                    val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, nombresMascotas)
                    listView.adapter = adapter

                    listView.setOnItemClickListener { parent, view, position, id ->
                        val nombreMascotaSeleccionada = nombresMascotas[position]
                        val imagenMascotaSeleccionada = mascotasList?.get(position)?.get("fotoMascota")

                        // Obtener la descripción de la mascota seleccionada utilizando su posición en la lista de nombres
                        val descripcionMascotaSeleccionada = mascotasList?.get(position)?.get("descripcionMascota")

                        // Actualizar los textos con los valores seleccionados
                        bindingPet.mascotaTXT.text = nombreMascotaSeleccionada
                        bindingPet.descripcionTXT.text = descripcionMascotaSeleccionada

                        imagenMascotaUrl = imagenMascotaSeleccionada

                        // Cargar la foto de la mascota en el ImageButton
                        if (imagenMascotaUrl != null) {
                            Picasso.with(this)
                                .load(imagenMascotaUrl)
                                .fit() // Ajustar la imagen para que se ajuste al tamaño del ImageButton
                                .into(photo_pet)
                        } else {
                            // Si la mascota seleccionada no tiene imagen, mostrar una imagen predeterminada
                            photo_pet?.setImageResource(R.drawable.patita)
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
            val imageRef = storageReference?.child("animales/$imageFileName")

            // Subir la imagen a Firebase Storage
            imageRef?.putFile(selectedImageUri!!)
                ?.addOnSuccessListener { taskSnapshot ->
                    // Obtener la URL de descarga de la imagen subida
                    imageRef.downloadUrl.addOnSuccessListener { downloadUrl ->
                        // Actualizar la foto de la mascota en la base de datos
                        val email = auth.currentUser?.email
                        val nombreMascota = bindingPet.mascotaTXT.text.toString()

                        if (email != null) {
                            val usuarioActualRef = storage.collection("usuarios").document(email)
                            usuarioActualRef.get().addOnSuccessListener { document ->
                                if (document != null && document.exists()) {
                                    val mascotasList = document.get("Mascotas") as ArrayList<HashMap<String, String>>?
                                    if (mascotasList != null) {
                                        for (mascota in mascotasList) {
                                            val nombre = mascota["nombreMascota"]
                                            if (nombre == nombreMascota) {
                                                mascota["fotoMascota"] = downloadUrl.toString()
                                                break
                                            }
                                        }

                                        // Actualizar el campo "Mascotas" en la base de datos
                                        usuarioActualRef.update("Mascotas", mascotasList)
                                            .addOnSuccessListener {
                                                // Actualizar la URL de la imagen de la mascota seleccionada
                                                imagenMascotaUrl = downloadUrl.toString()
                                                cargarList()
                                                // Cargar la foto subida en el ImageButton
                                                Picasso.with(this).load(imagenMascotaUrl).fit().into(photo_pet)

                                                Toast.makeText(this, "Imagen subida exitosamente", Toast.LENGTH_SHORT).show()
                                            }
                                            .addOnFailureListener { e ->
                                                Toast.makeText(this, "Error al subir la imagen", Toast.LENGTH_SHORT).show()
                                            }
                                    }
                                }
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

