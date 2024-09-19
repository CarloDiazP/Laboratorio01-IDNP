package com.example.propuesto

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // obtener botones
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val btnShow = findViewById<Button>(R.id.btnShow)

        // obtener campos
        val edtName = findViewById<EditText>(R.id.edtName)
        val edtLastname = findViewById<EditText>(R.id.edtLastname)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val edtBloodGroup = findViewById<EditText>(R.id.edtBloodGroup)

        // listener para registrar
        btnRegister.setOnClickListener {
            val name = edtName.text.toString()
            val lastname = edtLastname.text.toString()
            val email = edtEmail.text.toString()
            val phone = edtPhone.text.toString()
            val bloodGroup = edtBloodGroup.text.toString()

            val info = "Nombres: $name, Apellidos: $lastname, Correo: $email, Numero: $phone, Grupo sanguineo: $bloodGroup\n"
            saveInFile(info)
        }

        // listener para mostrar en log
        btnShow.setOnClickListener {
            try {
                val file = File(filesDir, "registro.txt")
                if (file.exists()) {
                    val content = file.readText()
                    Log.d("RegistrosConfe", content)
                } else {
                    Log.d("RegistrosConfe", "El archivo no existe")
                }
            } catch (exc: Exception) {
                Log.e("ErrorLectura", "Error leyendo el archivo", exc)
            }
        }
    }

    private fun saveInFile(text: String) {
        try {
            val file = File(filesDir, "registro.txt")
            val fos = FileOutputStream(file, true)
            fos.write(text.toByteArray())
            fos.close()
        } catch (exc: Exception) {
            Log.e("ErrorEscritura", "Error escribiendo en el archivo", exc)
        }
    }
}