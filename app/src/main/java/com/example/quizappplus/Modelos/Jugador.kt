package com.example.quizappplus.Modelos

import java.io.Serializable

data class Jugador(var nombre:String,var puntuacion:Int, var usoCheats:Boolean = false,var posicion:Int,var porsentaje:Double = 0.0):Serializable