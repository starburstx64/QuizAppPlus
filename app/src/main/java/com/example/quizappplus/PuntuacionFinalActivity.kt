package com.example.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView

class PuntuacionFinalActivity : AppCompatActivity() {

    //region controlesVista
    //textViews
    private lateinit var puntajeFinalTextView:TextView
    //imageViews
    private lateinit var imagenFinalImageView:ImageView
    //listViews
    private lateinit var puntajesListView:ListView
    //Buttons
    private lateinit var menuPrincipalButton:Button
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntuacion_final)

        //region InicializarControles
        puntajeFinalTextView=findViewById(R.id.puntajeFinal)

        imagenFinalImageView=findViewById(R.id.imagenFinal)

        puntajesListView=findViewById(R.id.puntajes_ListView)

        menuPrincipalButton=findViewById(R.id.menu_button)
        //endregion
    }
}
