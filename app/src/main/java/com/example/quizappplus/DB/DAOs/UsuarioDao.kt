package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Query
import com.example.quizappplus.DB.Entidades.UsuarioEntity

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM Usuario WHERE idUsuario = :id")
    fun getUsuarioById(id : Int) : UsuarioEntity

    @Query("SELECT idConfiguracion FROM usuario WHERE idUsuario=:idUsuario")
    fun GetIdConfiguraciones(idUsuario:Int):Int

    @Query("SELECT * FROM Usuario WHERE userName = :userName and contraseña = :userPassword")
    fun getUsuario(userName : String, userPassword : String) : UsuarioEntity?
}