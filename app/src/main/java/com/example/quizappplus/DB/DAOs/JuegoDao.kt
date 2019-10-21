package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizappplus.DB.Entidades.JuegoEntity

@Dao
interface JuegoDao {

    @Insert
    fun CreateGameForUser(juego:JuegoEntity)

    @Query("SELECT * FROM juego WHERE idUsuario=:idUsuario")
    fun GetJuego(idUsuario: Int):JuegoEntity

    @Query("SELECT * FROM juego WHERE idJuego=:idJuego")
    fun GetJuegoById(idJuego:Int):JuegoEntity

    @Query("UPDATE juego SET estatusJuego=1 WHERE idUsuario=:idUsuario")
    fun StartGame(idUsuario: Int)

    @Update
    fun UpdateJuego(juego: JuegoEntity)
}