package com.example.quizappplus.Modelos

import com.example.quizappplus.Modelos.Opcion
import java.io.Serializable

data class Pregunta (val id:Int, val opciones:List<Opcion>, var contestada:Boolean=false, var correcta:Boolean=false, var ordenOpciones:List<Int> = listOf(), var usoPista:Boolean= false):
    Serializable