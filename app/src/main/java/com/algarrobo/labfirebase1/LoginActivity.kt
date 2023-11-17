package com.algarrobo.labfirebase1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val edtemail: EditText = findViewById(R.id.edtemail)
        val edtpss: EditText = findViewById(R.id.edtpss)
        val btnlogin: Button = findViewById(R.id.btnlogin)

        val db = FirebaseAuth.getInstance()


        btnlogin.setOnClickListener {
            // lo que escribe el usuario
            var correo: String = edtemail.text.toString()
            var clave: String = edtpss.text.toString()

            db.signInWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Inicio de sesión exitoso",
                                Snackbar.LENGTH_LONG
                            ).show()
                        startActivity(Intent(this, PrincipalActivity::class.java))
                    } else {
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Credenciales inválidas",
                                Snackbar.LENGTH_LONG
                            ).show()
                    }
                }
        }


    }
}