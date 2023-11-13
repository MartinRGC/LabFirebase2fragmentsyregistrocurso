package com.algarrobo.labfirebase1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = FirebaseFirestore.getInstance()

        val tvcurso: TextView = findViewById(R.id.tvcurso)
        val tvnota: TextView = findViewById(R.id.tvnota)

        // coleccion de firebase

        db.collection("courses")
            .addSnapshotListener{ snapshots, e ->
                if(e != null){
                    Log.w("Firebase", "Listen:error",e) // warning
                    return@addSnapshotListener
                }
                for (dc in snapshots!!.documentChanges){
                    when (dc.type) { // cuando sea del tipo
                        DocumentChange.Type.ADDED ->{
                            Log.d("Firebase", "Data: "+ dc.document.data)
                            tvcurso.text = dc.document.data["description"].toString() // Data que se desea leer
                            tvnota.text = dc.document.data["score"].toString() // Data que se desea leer
                        }
                        DocumentChange.Type.MODIFIED -> {
                            tvcurso.text = dc.document.data["description"].toString()
                            tvnota.text = dc.document.data["score"].toString()
                        }
                        DocumentChange.Type.REMOVED ->{
                            tvcurso.text = "Curso eliminado"
                            tvnota.text = "Nota eliminada"
                        }

                    }
                }

            }
    }
}