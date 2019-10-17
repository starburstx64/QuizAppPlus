package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Query
import com.example.quizappplus.DB.Entidades.JuegoEntity

@Dao
interface JuegoDao {

    @Query("SELECT * FROM Juego WHERE idUsuario=:idUsuario")
    fun GetJuego(idUsuario: Int):JuegoEntity
}