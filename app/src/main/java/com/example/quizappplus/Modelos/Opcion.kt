package com.example.quizappplus.Modelos

import java.io.Serializable

data class Opcion(val opcion:String ,val answer:Boolean,var usedCheat:Boolean=false): Serializable