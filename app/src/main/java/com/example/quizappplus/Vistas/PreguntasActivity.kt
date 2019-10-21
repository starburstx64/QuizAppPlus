package com.example.quizappplus.Vistas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.Modelos.Configuraciones
import com.example.quizappplus.Modelos.Usuario
import com.example.quizappplus.R
import com.example.quizappplus.VistaModelos.PreguntasVM
import kotlinx.android.synthetic.main.activity_preguntas.*
import kotlin.random.Random

const val PREGUNTAS_REQUEST_CODE = 1300
const val EXTRA_CONFIGURACIONES_FOR_QUESTIONS =
    "com.example.quizappPlus.EXTRA_CONFIGURACIONES_FOR_QUESTIONS"
const val EXTRA_RESULT_LISTA_ORDENADA_NUEVA =
    "com.example.quizappPlus.EXTRA_RESULT_LISTA_ORDENADA_NUEVA"
const val EXTRA_PUNTUACIONES_LIST_FORQUESTIONS =
    "com.example.quizappPlus.EXTRA_PUNTUACIONES_LIST_FORQUESTIONS"

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
    private lateinit var lypreguntas: LinearLayout
    //endregion

    private lateinit var Configuraciones: Configuraciones
    private val model by lazy { ViewModelProviders.of(this)[PreguntasVM::class.java] }

    private lateinit var arregloPuntuaciones: MutableList<Usuario>

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

        //Cargamos las configuraciones iniciales del juego
        var database = AppDatabase.getAppDatabase(this)
        var juegoIniciado = intent.getBooleanExtra("existeJuegoGuardado",false)
        model.idUsuario = intent.getIntExtra("idUsuarioActivo",0)
        model.InicializarJuego(database,juegoIniciado)
        //Asignamos las configuraciones a esta variable para poder llamarlas directamente
        Configuraciones=model.configuraciones

        //Inicializamos los controles de la vista
        InicializarVista()

        //region Cosas de pistas
        //Si las pistas estan habilitadas, se muestra el control
        contPistasTextView.isVisible = Configuraciones.pistas
        //ponemos el contador de pistass
        if (Configuraciones.pistas) {
            contPistasTextView.text =
                "Pista ${model.getPistasUsadas()}/${Configuraciones.numPistas}"
        }
        //Al darle click al textView de pistas, se usa una, mientras no hayamos usado todas
        contPistasTextView.setOnClickListener {
            if (model.getCurrentQuestion().contestada == false && (model.getPistasUsadas() != Configuraciones.numPistas)) {
                UsarPista()
            }
        }
        //endregion

        //region NavegacionPreguntas
        siguiente_button.setOnClickListener {
            model.nextQuestion()
            updateQuestion()
        }
        anterior_button.setOnClickListener {
            model.previousQuestion()
            updateQuestion()
        }
        //endregion

        //region ResponderBotones
        btnRespuesta1.setOnClickListener {
            model.ContestarPregunta(0)
            updateQuestion()
        }
        btnRespuesta2.setOnClickListener {
            model.ContestarPregunta(1)
            updateQuestion()
        }
        btnRespuesta3.setOnClickListener {
            model.ContestarPregunta(2)
            updateQuestion()
        }
        btnRespuesta4.setOnClickListener {
            model.ContestarPregunta(3)
            updateQuestion()
        }
        //endregion
    }

    private fun InicializarVista()
    {
        //Configuramos los botones segun dificultad
        HabilitateButtons(model.configuraciones.dificultad)
        //Ponemos la primera pregunta
        updateQuestion()
    }

    private fun updateQuestion() {
        //Ponemos la pregunta
        preguntaTextView.setText(model.getCurrentQuestion().texto)
//        //ahora vamos a poner las opciones
        SetButtons(Configuraciones.dificultad)
        //Con esto sabemos si la pregunta fue contestada o no
        val flagContestada: Boolean = (model.getCurrentQuestion().contestada)
        //Ponemos el contador de preguntas en el valor que corresponde
        contPreguntasTextView.text = "Pregunta:${model.numQuestion + 1}/${model.numOfQuestions}"
        contPistasTextView.text =
            "Pista ${model.getPistasUsadas()}/${Configuraciones.numPistas}"
        //Averiguamos si uso pista para contestar la pregunta
        val usoPista =
            if (model.getCurrentQuestion().usoPista) " con pista" else ""
        //ponemos el estado de la pregunta en el textview
        val estadoPregunta =
            if (flagContestada == false) {
                getString(R.string.validacion_pregunta_3)
            } else {
                if (model.getCurrentQuestion().correcta) getString(R.string.validacion_pregunta_2) + usoPista
                else getString(R.string.validacion_pregunta_1) + usoPista

            }
        //Si ya se habia visto la pregunta se muestra el estado
        estadoPreguntaTextView.isVisible = model.flagQuestion
        //se pone que ya se vio la pregunta
        model.ActualizaFlag()
        //Se pone el texto de estado en el textview
        estadoPreguntaTextView.setText(estadoPregunta.toString())
        //Actualizamos los botones
        SetButtons(Configuraciones.dificultad)
        //si el juego ya termino pero volviste aqui, no se va a activar el menu de puntuacion
        if (model.juegoTerminado == false) {
            //Si ya contestaste todas las preguntas, se termina el juego
            if (model.PreguntasContestadas == model.numOfQuestions) {
                TerminarJuego()
                model.juegoTerminado = true
            }
        }

    }

    private fun SetButtons(dificultad: Int) {
        //Obtenemos los botones de respuestas
        var opcionesbtns: List<Button> = listOf(
            btnRespuesta1, btnRespuesta2, btnRespuesta3, btnRespuesta4
        )
        //Para cada boton segun la dificultad seleccionada
        for (i in 0..dificultad) {
            //ordenOpciones[i] me entrega que opcion corresponde segun el boton.
            // este orden cambia de juego a juego para agregar aleatoreidad
            opcionesbtns[i].setText(model.GetOpcionPreguntaActual(i).texto)
            //Habilito los botones si es que la pregunta no se ha contestado
            opcionesbtns[i].isEnabled = !(model.getCurrentQuestion().contestada)
            //Si la pregunta no se ha contestado checo si no se habia deshabilitado
            // algun boton por un cheat y si es así, lo deshabilito
            if (model.getCurrentQuestion().contestada == false)
                opcionesbtns[i].isEnabled = !(model.GetOpcionPreguntaActual(i).usedCheat)
        }
    }


    private fun HabilitateButtons(dificultad: Int) {
        when (dificultad) {
            1 -> {      //En facil deshabilitamos dos botones
                btnRespuesta3.isEnabled = false
                btnRespuesta3.isVisible = false
                btnRespuesta4.isEnabled = false
                btnRespuesta4.isVisible = false
            }
            2 -> {      //En medio deshabilitamos un boton
                btnRespuesta4.isEnabled = false
                btnRespuesta4.isVisible = false
            }
        }
    }

    private fun UsarPista() {
        //primero consigamos los botones
        var opcionesbtns: List<Button> = listOf(
            btnRespuesta1, btnRespuesta2, btnRespuesta3, btnRespuesta4
        )
        //ahora veamos cuales podemos usar y los indices de ellos
        var botonesUtilizables: MutableList<Button> = mutableListOf()
        var indicesBotonesUtilizables = mutableListOf<Int>()
        for (i in 0..Configuraciones.dificultad) {
            if (model.GetOpcionPreguntaActual(i).answer == false && opcionesbtns[i].isEnabled == true) {
                botonesUtilizables.add(opcionesbtns[i])
                indicesBotonesUtilizables.add(i)
            }
        }
        //ya que sabemos cuales podemos utilizar, seleccionemos uno al azar y quitemoslo
        val opcionAleatoria = Random.nextInt(0, botonesUtilizables.size)
        botonesUtilizables[opcionAleatoria].isEnabled = false
        var indiceBoton=indicesBotonesUtilizables[opcionAleatoria]
        //A la opcion correspondiente a ese boton que deshabilitamos, la vamos a marcar como que fue
        //contestada con un cheat
        model.GetOpcionPreguntaActual(indiceBoton).usedCheat=true
        var indiceOpcion = model.GetIndiceRealOpcion(indiceBoton)

        //Aveririguamos cuantos botones disponibles nos quedan y la cual de los botones es este
        var contEnables = 0
        var posEnabled=0
        for (i in 0..Configuraciones.dificultad) {
            if (opcionesbtns[i].isEnabled) {
                contEnables++
                posEnabled=i
            }

        }
        //Si solo queda un boton disponible, este es el de la respuesta correcta,
        //Asi que automaticamente contestamos la pregunta
        if (contEnables == 1) {
            model.ContestarPregunta(posEnabled)
            updateQuestion()
        }
        //Ponemos que usamos la pista
        model.usarPista(indiceOpcion)
        //Modificamos el contador de pistas
        contPistasTextView.text =
            "Pista ${model.getPistasUsadas()}/${Configuraciones.numPistas}"
    }

    fun TerminarJuego()
    {
        var database = AppDatabase.getAppDatabase(this)
        model.TerminarJuego(database)
    }
}
