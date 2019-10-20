package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.example.quizappplus.DB.Entidades.JuegoEntity

@Dao
interface JuegoDao {

    @Query("SELECT * FROM Juego WHERE idUsuario=:idUsuario")
    fun GetJuego(idUsuario: Int):JuegoEntity

    @Update
    fun UpdateJuego(juego: JuegoEntity)
}