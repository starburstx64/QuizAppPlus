package com.example.quizappplus

import java.io.Serializable

data class Categoria(val nombre:String,var seleccionada:Boolean,var preguntas:List<Pregunta>):Serializable