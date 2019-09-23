package com.example.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_preguntas.*

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
    private lateinit var ConfiguracionesModel: ConfiguracionesVM
    private val model by lazy { ViewModelProviders.of(this)[GameVM::class.java] }


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

        //Asignar el modelo de las configuraciones
        ConfiguracionesModel =
            intent.getSerializableExtra("EXTRA_CONFIGURACIONES_VIEWMODEL_FORQUESTIONS") as ConfiguracionesVM
        //Sacar la lista con las categorias que usaremos
        val CategoriasUsadas: List<Categoria> = ConfiguracionesModel.GetCategoriasUsadas()
        //y usarla para escoger las preguntas al azar
        model.SetQuestions(CategoriasUsadas, ConfiguracionesModel.numPregunta)
        model.SetQuestionsOptions(ConfiguracionesModel.dificultad)
        //Ponemos las cosas a la dificultad adecuada
        SetDificulty(ConfiguracionesModel.dificultad)
        //Ya que tenemos las preguntas hay que poner la primera
        updateQuestion()

        siguiente_button.setOnClickListener {
            model.nextQuestion()
            updateQuestion()
        }
        anterior_button.setOnClickListener {
            model.previousQuestion()
            updateQuestion()
        }

        btnRespuesta1.setOnClickListener {
            ComprobarRespuesta(0)
            updateQuestion()
        }
        btnRespuesta2.setOnClickListener {
            ComprobarRespuesta(1)
            updateQuestion()
        }
        btnRespuesta3.setOnClickListener {
            ComprobarRespuesta(2)
            updateQuestion()
        }
        btnRespuesta4.setOnClickListener {
            ComprobarRespuesta(3)
            updateQuestion()
        }
    }

    private fun updateQuestion() {
        //Ponemos la pregunta
        preguntaTextView.setText(model.getCurrentQuestion().id)
        //ahora vamos a poner las opciones
        SetOpciones(ConfiguracionesModel.dificultad)
        //Con esto sabemos si la pregunta fue contestada o no
        val contestada: Boolean = (model.getCurrentQuestion().contestada)
        //Con esto ponemos el contador de preguntas como debe
        contPreguntasTextView.setText("Pregunta:${model.numQuestion + 1}/${model.numOfQuestions}")
        //Esto nos sirve para poner el estado de la pregunta en el tv de abajo
        val estadoPregunta =
            if (contestada == false) {
                getString(R.string.validacion_pregunta_3)
            } else {
                if (model.getCurrentQuestion().correcta) getString(R.string.validacion_pregunta_2)
                else getString(R.string.validacion_pregunta_1)
            }
        estadoPreguntaTextView.isVisible=model.flagQuestion
        model.ActualizaFlag()
        estadoPreguntaTextView.text = estadoPregunta
        //Y ahora vamos a ver si los botones deben estar habilitados o no
        HabilitarBotones(ConfiguracionesModel.dificultad)
    }

    private fun SetOpciones(dificultad: Int) {
        val pregunta = model.getCurrentQuestion()
        val opciones = pregunta.opciones
        val ordenOpciones = pregunta.ordenOpciones
        var opcionesbtns: List<Button> = listOf(
            btnRespuesta1, btnRespuesta2, btnRespuesta3, btnRespuesta4
        )
        for (i in 0..dificultad) {
            opcionesbtns[i].setText(opciones[ordenOpciones[i]].opcion)
        }
    }

    private fun HabilitarBotones(dificultad: Int){
        var opcionesbtns: List<Button> = listOf(
            btnRespuesta1, btnRespuesta2, btnRespuesta3, btnRespuesta4
        )
        for (i in 0..dificultad) {
            opcionesbtns[i].isEnabled=!(model.getCurrentQuestion().contestada)
        }
    }

    private fun ComprobarRespuesta(index: Int) {
        model.getCurrentQuestion().contestada = true
        val correcta = GetResultSelectedOption(index)
        model.getCurrentQuestion().correcta = correcta
    }

    fun GetResultSelectedOption(index: Int): Boolean {
        val pregunta = model.getCurrentQuestion()
        val opciones = pregunta.opciones
        val ordenOpciones = pregunta.ordenOpciones
        return opciones[ordenOpciones[index]].answer
    }

    private fun SetDificulty(dificultad: Int) {
        when (dificultad) {
            1 -> {
                btnRespuesta3.isEnabled = false
                btnRespuesta3.isVisible = false
                btnRespuesta4.isEnabled = false
                btnRespuesta4.isVisible = false
            }
            2 -> {
                btnRespuesta4.isEnabled = false
                btnRespuesta4.isVisible = false
            }
        }
    }


}
