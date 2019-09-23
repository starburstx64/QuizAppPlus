package com.example.quizappplus

import java.io.Serializable

data class Pregunta (val id:Int,val opciones:List<Opcion>,var contestada:Boolean=false, var correcta:Boolean=false):
    Serializable