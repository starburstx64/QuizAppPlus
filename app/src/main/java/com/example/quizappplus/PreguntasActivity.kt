package com.example.quizappplus

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_configuraciones.*
import kotlinx.android.synthetic.main.activity_preguntas.*
import kotlin.random.Random

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

    private lateinit var arregloPuntuaciones:MutableList<Jugador>


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

        arregloPuntuaciones = intent.getSerializableExtra("EXTRA_PUNTUACIONES_LIST_FORQUESTIONS") as ArrayList<Jugador>

        InicializarJuego()

        //region Cosas de pistas
        contPistasTextView.isVisible=ConfiguracionesModel.pistas
        if (ConfiguracionesModel.pistas)
        {
            contPistasTextView.text = "Pista ${model.getPistasUsadas()}/${ConfiguracionesModel.numPistas}"
        }
        contPistasTextView.setOnClickListener{
            if(model.getCurrentQuestion().contestada==false && (model.getPistasUsadas()!=ConfiguracionesModel.numPistas)) {
                UsarPista()
            }
        }
        //endregion

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
            model.ContestarPregunta()
            updateQuestion()
        }
        btnRespuesta2.setOnClickListener {
            ComprobarRespuesta(1)
            model.ContestarPregunta()
            updateQuestion()
        }
        btnRespuesta3.setOnClickListener {
            ComprobarRespuesta(2)
            model.ContestarPregunta()
            updateQuestion()
        }
        btnRespuesta4.setOnClickListener {
            ComprobarRespuesta(3)
            model.ContestarPregunta()
            updateQuestion()
        }
    }

    private fun InicializarJuego(){
        if(!model.FlagJuegoIniciado) {
            //Sacar la lista con las categorias que usaremos
            val CategoriasUsadas: List<Categoria> = ConfiguracionesModel.GetCategoriasUsadas()
            //y usarla para escoger las preguntas al azar
            model.SetQuestions(CategoriasUsadas, ConfiguracionesModel.numPregunta)
            model.SetQuestionsOptions(ConfiguracionesModel.dificultad)
            //Ponemos las cosas a la dificultad adecuada
            model.SetJuegoIniciado()
        }
        SetDificulty(ConfiguracionesModel.dificultad)
        //Ya que tenemos las preguntas hay que poner la primera
        updateQuestion()
    }

    private fun updateQuestion() {
        //Ponemos la pregunta
        preguntaTextView.setText(model.getCurrentQuestion().id)
        //ahora vamos a poner las opciones
        SetOpciones(ConfiguracionesModel.dificultad)
        //Con esto sabemos si la pregunta fue contestada o no
        val contestada: Boolean = (model.getCurrentQuestion().contestada)
        //Con esto ponemos el contador de preguntas como debe
        contPreguntasTextView.text = "Pregunta:${model.numQuestion + 1}/${model.numOfQuestions}"
        contPistasTextView.text = "Pista ${model.getPistasUsadas()}/${ConfiguracionesModel.numPistas}"
        //Esto nos sirve para poner el estado de la pregunta en el tv de abajo
        val usoPista =
            if (model.getCurrentQuestion().usoPista) " con pista" else ""
        val estadoPregunta =
            if (contestada == false) {
                getString(R.string.validacion_pregunta_3)
            } else {
                if (model.getCurrentQuestion().correcta) getString(R.string.validacion_pregunta_2) + usoPista
                else getString(R.string.validacion_pregunta_1) + usoPista
            }
        estadoPreguntaTextView.isVisible=model.flagQuestion
        model.ActualizaFlag()
        estadoPreguntaTextView.text = estadoPregunta
        //Y ahora vamos a ver si los botones deben estar habilitados o no
        HabilitarBotones(ConfiguracionesModel.dificultad)
        if(model.PreguntasContestadas==model.numOfQuestions){
            TerminarJuego()
        }

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

    private fun UsarPista(){
        //primero consigamos los botones
        var opcionesbtns: List<Button> = listOf(
            btnRespuesta1, btnRespuesta2, btnRespuesta3, btnRespuesta4
        )
        //ahora veamos cuales podemos usar
        var botonesUtilizables:MutableList<Button> = mutableListOf()
        for (i in 0..ConfiguracionesModel.dificultad){
            if (GetResultSelectedOption(i)==false && opcionesbtns[i].isEnabled==true)
                botonesUtilizables.add(opcionesbtns[i])
        }
        //ya que sabemos cuales podemos utilizar, seleccionemos uno al azar y quitemoslo
        botonesUtilizables[Random.nextInt(0,botonesUtilizables.size)].isEnabled=false
        var contEnables:Int = 0
        for (i in 0..ConfiguracionesModel.dificultad){
            if (opcionesbtns[i].isEnabled)
                contEnables++
        }
        if (contEnables==1)
        {
            model.getCurrentQuestion().contestada=true
            model.getCurrentQuestion().correcta=true
            updateQuestion()
        }
        model.usarPista()
        contPistasTextView.text = "Pista ${model.getPistasUsadas()}/${ConfiguracionesModel.numPistas}"
    }

    private fun TerminarJuego(){
        val intent: Intent = Intent(this, NombreJugadorActivity::class .java)
        startActivityForResult(intent, NOMBREJUGADOR_ACTIVITY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            NOMBREJUGADOR_ACTIVITY_REQUEST_CODE->{
                when(resultCode){
                    Activity.RESULT_OK->{
                        model.SetNombre(data?.getStringExtra(EXTRA_RESULT_TEXT) as String)
                        arregloPuntuaciones.add(Jugador(model.NombreJugador,950,model.FlagUsoPista,0))
                        ordenarPuntajes()

                        val otroIntent: Intent = Intent(this,PuntuacionFinalActivity::class.java)
                        otroIntent.putExtra("EXTRA_LISTA_PUNTUACIONES",arregloPuntuaciones as ArrayList<Jugador>)
                        startActivity(otroIntent)
                    }
                    Activity.RESULT_CANCELED->model.SetNombre("AAA")
                }
            }
        }
    }

    private fun ordenarPuntajes(){
        arregloPuntuaciones.sortWith(compareBy({it.puntuacion},{it.posicion},{!(it.usoCheats)}))
        arregloPuntuaciones.reverse()
    }


}
