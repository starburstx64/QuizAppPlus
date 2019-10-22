package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizappplus.DB.Entidades.PuntuacionEntity

@Dao
interface PuntuacionDao {

    @Insert
    fun InsertarPuntuacion(puntuacion:PuntuacionEntity):Long

    @Query("DELETE FROM puntuacion WHERE idUsuario=:idUsuario")
    fun DeletePuntuaciones(idUsuario:Int)

}