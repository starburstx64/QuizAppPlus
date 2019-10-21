package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizappplus.DB.Entidades.PuntuacionEntity

@Dao
interface PuntuacionDao {

    @Insert
    fun InsertarPuntuacion(puntuacion:PuntuacionEntity):Long

    data class PuntuacionReport(
        val nombreUsuario:String,
        val puntuacion:Int,
        val cheated:Boolean,
        val imagenAvatar:Int
    )


    @Query(
        "SELECT usr.userName AS nombreUsuario,pun.puntuacion AS puntuacion,pun.cheated AS cheated, usr.ImagenAvatar AS imagenAvatar" +
                "FROM Puntuacion pun " +
                "INNER JOIN Usuario usr ON (pun.idUsuario=usr.idUsuario) " +
                "ORDER BY pun.puntuacion DESC, pun.cheated ASC, pun.idPuntuacion DESC " +
                "LIMIT 6"
    )
    fun GetPuntuacionesReport():List<PuntuacionReport>

}