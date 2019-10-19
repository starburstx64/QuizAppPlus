package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PreguntaJuegoDao {

    @Query("DELETE FROM PreguntaJuego WHERE idJuego=:idJuego")
    fun DeleteGameQuestions(idJuego:Int)
}