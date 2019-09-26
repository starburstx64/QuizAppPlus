package com.example.quizappplus

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.math.log
import kotlin.random.Random

class GameVM : ViewModel() {
    private var flagJuegoIniciado = false
    private var flagUsoPista = false
    var juegoTerminado=false
    private lateinit var questions: List<Pregunta>
    private lateinit var flagQuestions: MutableList<Boolean>
    private var currentQuestion: Int = 0
    private lateinit var nombreJugador: String
    private var preguntasContestadas = 0
    private var pistasUsadas: Int = 0

    //propiedades
    val FlagJuegoIniciado get() = flagJuegoIniciado
    val PreguntasContestadas get() = preguntasContestadas
    val NombreJugador get() = nombreJugador
    val numOfQuestions get() = questions.size
    val numQuestion get() = currentQuestion
    val flagQuestion get() = flagQuestions[currentQuestion]
    val FlagUsoPista get() = flagUsoPista

    fun UsoPista(){
        flagUsoPista = true
    }

    fun SetJuegoIniciado() {
        flagJuegoIniciado = true
    }

    fun ActualizaFlag() {
        flagQuestions[currentQuestion] = true
    }

    fun ContestarPregunta() {
        preguntasContestadas++
    }

    fun SetNombre(nombre: String) {
        nombreJugador = nombre
    }

    fun getPistasUsadas() = pistasUsadas
    fun usarPista() {
        pistasUsadas++
        UsoPista()
        questions[currentQuestion].usoPista=true
    }

    fun SetQuestions(categorias: List<Categoria>, numPreguntas: Int) {
        //Sacar todas las preguntas de las categorias seleccionadas
        var preguntasPotenciales: MutableList<Pregunta> = mutableListOf()
        for (i in 0..categorias.size - 1) {
            preguntasPotenciales.addAll(categorias[i].preguntas)
        }
        //Escoge aleatoriamente las preguntas
        var preguntasSeleccionadas: MutableList<Pregunta> = mutableListOf()
        for (i in 0..numPreguntas - 1) {
            var selectedIndex = Random.nextInt(0, preguntasPotenciales.size)
            preguntasSeleccionadas.add(preguntasPotenciales[selectedIndex])
            preguntasPotenciales.removeAt(selectedIndex)
        }
        questions = preguntasSeleccionadas
        setFlags()
    }

    private fun setFlags() {
        var listaFlags: MutableList<Boolean> = mutableListOf()
        for (i in 0..questions.size - 1) {
            listaFlags.add(false)
        }
        flagQuestions = listaFlags
    }

    fun SetQuestionsOptions(dificultad: Int) {
        for (i in 0..questions.size - 1) {
            var numDisponibles: MutableList<Int> = mutableListOf(1, 2, 3)
            when (dificultad) {
                1 -> {
                    numDisponibles.removeAt(Random.nextInt(0, numDisponibles.size))
                    numDisponibles.removeAt(Random.nextInt(0, numDisponibles.size))
                }
                2 -> {
                    numDisponibles.removeAt(Random.nextInt(0, numDisponibles.size))
                }
            }
            numDisponibles.add(0)
            var ordenOpciones: MutableList<Int> = mutableListOf()
            for (j in 0..dificultad) {
                var selectedIndex = Random.nextInt(0, numDisponibles.size)
                ordenOpciones.add(numDisponibles[selectedIndex])
                numDisponibles.removeAt(selectedIndex)
            }
            questions[i].ordenOpciones = ordenOpciones
        }
    }

    fun getCurrentQuestion() = questions[currentQuestion]

    fun getQuestion(index: Int) = questions[index]

    fun nextQuestion() {
        currentQuestion = (currentQuestion + 1) % questions.size
    }

    fun previousQuestion() {
        currentQuestion = (currentQuestion + questions.size - 1) % questions.size
    }

    fun GetNumRespuestasCorrectas():Int{
        var contRespuestasCorrectas = 0
        for (i in 0 until questions.size)
        {
            contRespuestasCorrectas=
                if (questions[i].correcta)
                    contRespuestasCorrectas +1
                else
                    contRespuestasCorrectas
        }
        return contRespuestasCorrectas
    }

    fun GetAcuracy():Double{
        return (GetNumRespuestasCorrectas().toDouble()/numOfQuestions.toDouble())*100.0
    }

    fun GetPuntajeFinal():Int{
        var puntaje:Int = 0
        for (i in 0 until questions.size)
        {
            if (questions[i].correcta)
            {
                if (questions[i].usoPista)
                {
                    puntaje+=50
                }
                else
                    puntaje+=100
            }
        }
        return puntaje
    }
}