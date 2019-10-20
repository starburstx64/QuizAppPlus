package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizappplus.DB.Entidades.PreguntaJuegoEntity

@Dao
interface PreguntaJuegoDao {

    @Query("DELETE FROM PreguntaJuego WHERE idJuego=:idJuego")
    fun DeleteGameQuestions(idJuego:Int)

    @Insert
    fun InsertarPreguntaJuego(preguntaJuego: PreguntaJuegoEntity)
}