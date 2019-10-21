package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Query
import com.example.quizappplus.DB.Entidades.AplicacionEntity

@Dao
interface AplicacionDao {

    @Query("SELECT * FROM Aplicacion")
    fun getAll():List<AplicacionEntity>

    @Query("UPDATE Aplicacion SET idUsuarioActivo = :id WHERE idAplicacion = 0")
    fun setIdUsuarioActivo(id : Int?)

    @Query("SELECT idUsuarioActivo FROM Aplicacion WHERE idAplicacion = 0")
    fun getIdUsuarioActivo() : Int?
}