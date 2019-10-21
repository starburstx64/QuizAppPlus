package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizappplus.DB.Entidades.PreguntaJuegoEntity

@Dao
interface PreguntaJuegoDao {

    @Query("DELETE FROM PreguntaJuego WHERE idJuego=:idJuego")
    fun DeleteGameQuestions(idJuego:Int)

    @Query("SELECT * FROM preguntajuego WHERE idJuego=:idJuego ORDER BY ordenEnJuego")
    fun GetPreguntasUsadas(idJuego:Int):List<PreguntaJuegoEntity>

    @Insert
    fun InsertarPreguntaJuego(preguntaJuego: PreguntaJuegoEntity)


}