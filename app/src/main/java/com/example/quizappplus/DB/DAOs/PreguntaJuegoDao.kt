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

    @Query("SELECT COUNT(idPregunta) FROM preguntajuego GROUP BY idJuego HAVING idJuego=:idJuego AND contestada=1")
    fun GetNumberAnsweredQuestions(idJuego: Int):Int

    @Insert
    fun InsertarPreguntaJuego(preguntaJuego: PreguntaJuegoEntity)


}