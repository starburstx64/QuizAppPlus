package com.example.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class PreguntasActivity : AppCompatActivity() {

    //region controlesVista
    //textViews
    private lateinit var contPreguntasTextView: TextView
    private lateinit var contPistasTextView: TextView
    private lateinit var preguntaTextView: TextView
    private lateinit var estadoPreguntaTextView: TextView
    //Botones respuestas
    private lateinit var btnRespuesta1: Button
    private lateinit var btnRespuesta2: Button
    private lateinit var btnRespuesta3: Button
    private lateinit var btnRespuesta4: Button
    //Btotones navegacion
    private lateinit var btnAvanzar: Button
    private lateinit var btnRetroceder: Button
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preguntas)
        //region InicializadorControles
        contPreguntasTextView = findViewById(R.id.pregunta_contador)
        contPistasTextView = findViewById(R.id.pista_contador)
        preguntaTextView = findViewById(R.id.preguntas)

        btnRespuesta1 = findViewById(R.id.respuesta1)
        btnRespuesta2 = findViewById(R.id.respuesta2)
        btnRespuesta3 = findViewById(R.id.respuesta3)
        btnRespuesta4 = findViewById(R.id.respuesta4)

        btnAvanzar = findViewById(R.id.siguiente_button)
        btnRetroceder = findViewById(R.id.anterior_button)

        estadoPreguntaTextView = findViewById(R.id.pregunta_estado)
        //endregion
    }
}
