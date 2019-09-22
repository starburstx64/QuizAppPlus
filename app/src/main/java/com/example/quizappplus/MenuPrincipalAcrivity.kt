package com.example.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuPrincipalAcrivity : AppCompatActivity() {

    //region controlesVista
    private lateinit var btnJuego:Button
    private lateinit var btnOpciones:Button
    private lateinit var btnPuntuacion:Button
    //endregion
    //region estadosAndroid
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        //region inicializarControles
        btnJuego = findViewById(R.id.juego_button)
        btnOpciones = findViewById(R.id.opciones_button)
        btnPuntuacion = findViewById(R.id.puntuacion_button)
        //endregion
    }
    //endregion
}
