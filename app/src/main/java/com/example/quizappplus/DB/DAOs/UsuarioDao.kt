package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.DatabaseConfiguration
import androidx.room.Delete
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

    @Query("SELECT * FROM Usuario WHERE userName = :userName")
    fun getUsuarioByName(userName : String) : UsuarioEntity?

    @Query("INSERT INTO Usuario (idConfiguracion, idAplicacion, imagenAvatar, userName, contraseña) VALUES (:idConfiguracion, 0, :imagenAvatar, :userName, :userPassword)")
    fun insertUsuario(idConfiguracion: Int, imagenAvatar : Int, userName: String, userPassword: String):Long

    @Query("UPDATE Usuario SET userName = :userName, contraseña = :userPassword, imagenAvatar = :photoid WHERE idUsuario = :id")
    fun actualizarUsuario(id : Int, userName : String, userPassword : String, photoid : Int)

    @Delete
    fun DeleteUsuario(usuarioEntity: UsuarioEntity)
}