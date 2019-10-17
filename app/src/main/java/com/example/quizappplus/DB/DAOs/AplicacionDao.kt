package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Query
import com.example.quizappplus.DB.Entidades.AplicacionEntity

@Dao
interface AplicacionDao {

    @Query("SELECT * FROM Aplicacion")
    fun getAll():List<AplicacionEntity>
}