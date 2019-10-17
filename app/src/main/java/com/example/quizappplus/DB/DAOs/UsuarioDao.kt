package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Query
import com.example.quizappplus.DB.Entidades.UsuarioEntity

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM Usuario WHERE idUsuario = :id")
    fun getUsuarioById(id : Int) : UsuarioEntity
}