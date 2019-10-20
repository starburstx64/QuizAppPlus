package com.example.quizappplus.Modelos

import com.example.quizappplus.DB.AppDatabase
import java.io.Serializable

data class Opcion(val idOpcion:Int ,val texto:String ,val answer:Boolean,var usedCheat:Boolean=false): Serializable
{

    companion object{

        fun GetQuestionOptions(db:AppDatabase,idPregunta:Int):List<Opcion>
        {
            var opcionesPregunta = mutableListOf<Opcion>()
            var entidadesOpciones = db.getOpcionDao().GetQuestionOptions(idPregunta)

            for (entidadOpcion in entidadesOpciones)
            {
                opcionesPregunta.add(Opcion(entidadOpcion.idOpcion!!,entidadOpcion.texto,entidadOpcion.isCorrect))
            }

            return opcionesPregunta
        }

    }

}