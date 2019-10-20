package com.example.quizappplus.Modelos

import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.DB.Entidades.JuegoEntity
import java.io.Serializable

data class Usuario(var nombre:String, var puntuacion:Int, var usoCheats:Boolean = false, var posicion:Int, var porsentaje:Double = 0.0):Serializable{

    companion object{
        fun GetGameStatus(db:AppDatabase,idUsuario:Int):Boolean{
            val juegoActual:JuegoEntity = db.getJuegoDao().GetJuego(idUsuario)
            return juegoActual.estatusJuego==1
        }

        fun GetActiveUserId(db:AppDatabase):Int
        {
            return db.getAplicacionDao().getIdUsuarioActivo()!!
        }

        fun EraseSavedGame(db: AppDatabase,idUsuario: Int)
        {
            val juegoActual:JuegoEntity = db.getJuegoDao().GetJuego(idUsuario)
            juegoActual.estatusJuego = 1
            juegoActual.numPistas = 0
            juegoActual.cheated = false
            db.getJuegoDao().UpdateJuego(juegoActual)
            db.getPreguntaJuegoDao().DeleteGameQuestions(juegoActual.idJuego!!)
        }
    }
}