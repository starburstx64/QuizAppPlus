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
        ConfiguracionesModel = intent.getSerializableExtra("EXTRA_CONFIGURACIONES_VIEWMODEL_FORQUESTIONS") as ConfiguracionesVM
        //Sacar la lista con las categorias que usaremos
        val CategoriasUsadas:List<Categoria> = ConfiguracionesModel.GetCategoriasUsadas()
        //y usarla para escoger las preguntas al azar
        model.SetQuestions(CategoriasUsadas,ConfiguracionesModel.numPregunta)
        model.SetQuestionsOptions(ConfiguracionesModel.dificultad)
        //Ponemos las cosas a la dificultad adecuada
        SetDificulty(ConfiguracionesModel.dificultad)
        //Ya que tenemos las preguntas hay que poner la primera
        updateQuestion()

        siguiente_button.setOnClickListener{
            model.nextQuestion()
            updateQuestion()
        }
        anterior_button.setOnClickListener{
            model.previousQuestion()
            updateQuestion()
        }

    }

    private fun updateQuestion(){
        //Ponemos la pregunta
        preguntaTextView.setText(model.getCurrentQuestion().id)
        //ahora vamos a poner las opciones
        SetOpciones(ConfiguracionesModel.dificultad)
        //Con esto sabemos si la pregunta fue contestada o no
        val contestada: Boolean = !(model.getCurrentQuestion().contestada)
        contPreguntasTextView.setText("Pregunta:${model.numQuestion+1}/${model.numOfQuestions}")
    }

    private fun SetOpciones(dificultad: Int){
        val pregunta= model.getCurrentQuestion()
        val opciones = pregunta.opciones
        val ordenOpciones = pregunta.ordenOpciones
        var opcionesbtns:List<Button> = listOf(
            btnRespuesta1,btnRespuesta2,btnRespuesta3,btnRespuesta4
        )
        for(i in 0..dificultad){
            opcionesbtns[i].setText(opciones[ordenOpciones[i]].opcion)
        }
    }
    private fun SetDificulty(dificultad:Int){
        when(dificultad){
            1->{
                btnRespuesta3.isEnabled=false
                btnRespuesta3.isVisible=false
                btnRespuesta4.isEnabled=false
                btnRespuesta4.isVisible=false
            }
            2->{
                btnRespuesta4.isEnabled=false
                btnRespuesta4.isVisible=false
            }
        }
    }


}
