package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Query("SELECT idConfiguracion FROM usuario WHERE idUsuario=:idUsuario")
    fun GetIdConfiguraciones(idUsuario:Int):Int
}