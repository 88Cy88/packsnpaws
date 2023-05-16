package sauceda.carlos.packsnpaws

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginLeft
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import sauceda.carlos.packsnpaws.databinding.ActivityCatalagoMascotasBinding
import android.view.ViewGroup.MarginLayoutParams
import android.view.ViewTreeObserver.OnScrollChangedListener
import androidx.annotation.RequiresApi
import androidx.core.view.setPadding


class CatalagoMascotas : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var storage: FirebaseFirestore
    private lateinit var bindingPet: ActivityCatalagoMascotasBinding

    var photo_pet: ImageView? = null
    //referencias a Storage de firebase
    var storageReference: StorageReference? = null
    var storage_path = "animales/*"
    private var image_url: Uri? = null
    private var imagenMascotaUrl: String? = null
    private lateinit var horizontalScrollView: HorizontalScrollView
    private var mascotasData: ArrayList<HashMap<String, String>>? = null
    private var currentMascotaIndex: Int = 0

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalago_mascotas)
        bindingPet = ActivityCatalagoMascotasBinding.inflate(layoutInflater)
        setContentView(bindingPet.root)

        auth = FirebaseAuth.getInstance()
        storage = FirebaseFirestore.getInstance()
        horizontalScrollView = findViewById(R.id.scrollMascotas)
        cargarList()

        val atrasBoton = findViewById<ImageButton>(R.id.back_btn)
        atrasBoton.setOnClickListener {
            val intent = Intent(this, HomeMenu::class.java)
            startActivity(intent)
        }


    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun cargarList() {
        val collectionRef = storage.collection("usuarios")
        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout) // Reemplaza con el ID real de tu LinearLayout

        collectionRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val documents = task.result?.documents

                if (!documents.isNullOrEmpty()) {
                    val mascotasData = mutableListOf<HashMap<String, String>>()

                    // Recorre todos los documentos (usuarios) y obtén las mascotas de cada uno
                    for (document in documents) {
                        val mascotasList = document.get("Mascotas") as ArrayList<HashMap<String, String>>?
                        if (mascotasList != null) {
                            mascotasData.addAll(mascotasList)
                        }
                    }

                    // Muestra los datos de la primera mascota en la lista
                    if (mascotasData.isNotEmpty()) {
                        val primeraMascota = mascotasData[0]
                        mostrarDatosMascota(primeraMascota)
                    }

                    for (i in mascotasData.indices) {
                        val mascota = mascotasData[i]
                        val imagenMascotaSeleccionada = mascota["fotoMascota"]
                        val nombreMascota = mascota["nombreMascota"]
                        val descripcionMascota = mascota["descripcionMascota"]

                        // Crea una ImageView para cada mascota
                        val imageView = ImageView(this)
                        val layoutParams = LinearLayout.LayoutParams(
                            resources.getDimensionPixelSize(R.dimen.image_width),
                            resources.getDimensionPixelSize(R.dimen.image_height)
                        )
                        layoutParams.leftMargin = resources.getDimensionPixelSize(R.dimen.layout_marginLeft)

                        // Verificar si es la última imagen cargada
                        if (i == mascotasData.size - 1) {
                            layoutParams.rightMargin = resources.getDimensionPixelSize(R.dimen.layout_marginRight_last)
                        } else {
                            layoutParams.rightMargin = resources.getDimensionPixelSize(R.dimen.layout_marginRight)
                        }

                        imageView.layoutParams = layoutParams

                        // Cargar la foto de la mascota en la ImageView utilizando Picasso
                        if (imagenMascotaSeleccionada != null) {
                            Picasso.with(this).load(imagenMascotaSeleccionada).fit().into(imageView)

                            // Agrega un listener al ImageView para detectar el clic en una imagen
                            imageView.setOnClickListener {
                                mostrarDatosMascota(mascota)
                                currentMascotaIndex = i // Actualiza el índice de la mascota actual
                            }
                        } else {
                            // Si la mascota no tiene imagen, mostrar una imagen predeterminada
                            imageView.setImageResource(R.drawable.patita)
                        }

                        // Agrega la ImageView al LinearLayout
                        linearLayout.addView(imageView)
                    }

                    // Agrega un listener al HorizontalScrollView para detectar los cambios de posición del scroll
                    horizontalScrollView.setOnScrollChangeListener { _, _, _, _, _ ->
                        val scrollX = horizontalScrollView.scrollX
                        val imageWidth = resources.getDimensionPixelSize(R.dimen.image_width)
                        val currentPosition = scrollX / imageWidth
                        if (currentPosition >= 0 && currentPosition < mascotasData.size) {
                            val mascota = mascotasData[currentPosition]
                            mostrarDatosMascota(mascota)
                            currentMascotaIndex = currentPosition
                        }
                    }
                }
            } else {
                // Ocurrió un error al obtener los documentos
                println("Error al obtener los documentos: ${task.exception}")
            }
        }
    }

    private fun mostrarDatosMascota(mascota: HashMap<String, String>) {
        val nombreMascota = mascota["nombreMascota"]
        val descripcionMascota = mascota["descripcionMascota"]
        bindingPet.nombrePet.text = nombreMascota
        bindingPet.descripcionCortaPet.text = descripcionMascota
    }

}