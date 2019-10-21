package com.example.quizappplus.Modelos

import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.DB.Entidades.PreguntaJuegoEntity
import com.example.quizappplus.Modelos.Opcion
import java.io.Serializable
import kotlin.random.Random

data class Pregunta (val id:Int,val texto:String,val opciones:List<Opcion>, var contestada:Boolean=false, var correcta:Boolean=false, var ordenOpciones:List<Int> = listOf(), var usoPista:Boolean= false):
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
                        pregunta.texto,
                        Opcion.GetQuestionOptions(db,pregunta.idPregunta)
                    )
                )
            }
            return preguntasDisponibles
        }

        fun GetNumAnsweredQuestions(db:AppDatabase,idJuego:Int):Int
        {
            return db.getPreguntaJuegoDao().GetNumberAnsweredQuestions(idJuego)
        }

        fun SetPreguntasUsadas(db:AppDatabase,PreguntasUsadas:List<Pregunta>)
        {
            var idUsuario = Usuario.GetActiveUserId(db)
            var idJuego = db.getJuegoDao().GetJuego(idUsuario).idJuego

            var ordenPregunta=1
            for (pregunta in PreguntasUsadas)
            {
                var ordenOpciones = setQuestionOptions()
                var preguntaJuego = PreguntaJuegoEntity(idJuego!!,pregunta.id,false,false,ordenPregunta,ordenOpciones,"",false)
                db.getPreguntaJuegoDao().InsertarPreguntaJuego(preguntaJuego)
                ordenPregunta++
            }
        }

        fun GetPreguntasUsadas(db:AppDatabase):List<Pregunta>
        {
            var preguntas = mutableListOf<Pregunta>()

            var idUsuario = Usuario.GetActiveUserId(db)
            var idJuego = db.getJuegoDao().GetJuego(idUsuario).idJuego

            var preguntasUsadas = db.getPreguntaJuegoDao().GetPreguntasUsadas(idJuego!!)

            for(pregunta in preguntasUsadas)
            {
                var opcionesPregunta = Opcion.GetQuestionOptions(db,pregunta.idPregunta)

                var textoPregunta = db.getPreguntaDao().SelectTextFromQuestion(pregunta.idPregunta)

                if (pregunta.optionsCheated!="")
                {
                    setOptionsCheatedProperty(pregunta.optionsCheated,opcionesPregunta)
                }

                preguntas.add(Pregunta(pregunta.idPregunta,textoPregunta,opcionesPregunta,pregunta.contestada,pregunta.correcta,
                    getQuestionOptionsOrder(pregunta.ordenOpciones),pregunta.cheated))
            }

            return preguntas
        }

        private fun setOptionsCheatedProperty(StringCheateds:String,opciones:List<Opcion>)
        {
            var opcionesTrampa = mutableListOf<Int>()

            var number=""
            for (i in 0 until StringCheateds.length) {
                if (StringCheateds[i] == '/') {
                    opcionesTrampa.add(number.toInt())
                    number = ""
                } else {
                    number += opciones[i]
                }
            }

            for (index in opcionesTrampa)
            {
                opciones[index].usedCheat=true
            }
        }

        private fun setQuestionOptions():String
        {
            var numDisponibles: MutableList<Int> = mutableListOf(0,1, 2, 3)

            var ordenOpciones: MutableList<Int> = mutableListOf()   //Con esta lista sabremos en que orden estaran las opciones
            for (j in 0 until numDisponibles.size) { //En facil se hara 2 veces, en medio 3 y en dificil 4
                var selectedIndex = Random.nextInt(0, numDisponibles.size)  //se selecciona un indice de los numeros disponibles
                ordenOpciones.add(numDisponibles[selectedIndex])    //Ese numero escogido al azar sera puesto en el orden de las opciones
                //Por ejemplo, si los disponibles eran el 0 y el 3, si se selecciona el 3 primero, significa que al boton 1
                //Se le asignara la opcion 3 de las opciones que tiene la pregunta
                numDisponibles.removeAt(selectedIndex)  //se quita la opcion de las opciones disponibles para que no se pueda repetir
            }
            var ordenConvertido = ""
            for (indice in ordenOpciones)
            {
                ordenConvertido+=indice
                ordenConvertido+='/'
            }
            return ordenConvertido
        }

        private fun getQuestionOptionsOrder(opciones:String):List<Int>
        {
            var ordenOpciones = mutableListOf<Int>()

            var number=""
            for (i in 0 until opciones.length) {
                if (opciones[i] == '/') {
                    ordenOpciones.add(number.toInt())
                    number = ""
                } else {
                    number += opciones[i]
                }
            }
            return ordenOpciones
        }

    }

}