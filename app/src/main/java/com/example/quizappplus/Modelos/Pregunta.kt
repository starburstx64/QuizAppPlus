package com.example.quizappplus.Modelos

import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.Modelos.Opcion
import java.io.Serializable

data class Pregunta (val id:Int, val opciones:List<Opcion>, var contestada:Boolean=false, var correcta:Boolean=false, var ordenOpciones:List<Int> = listOf(), var usoPista:Boolean= false):
    Serializable
{

    companion object{

        fun GetPreguntasDisponibles(db:AppDatabase,catDisponibles:List<Int>):List<Pregunta>
        {
            var preguntasDisponibles = mutableListOf<Pregunta>()

            var preguntas = db.getPreguntaDao().SelectQuestionsFromCategories(catDisponibles)
            for (pregunta in preguntas)
            {
                preguntasDisponibles.add(
                    Pregunta(
                        pregunta.idPregunta,
                        Opcion.GetQuestionOptions(db,pregunta.idPregunta)
                    )
                )
            }
            return preguntasDisponibles
        }

    }

}