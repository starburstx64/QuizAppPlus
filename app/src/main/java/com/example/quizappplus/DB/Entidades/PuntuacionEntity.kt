package com.example.quizappplus.DB.Entidades

import androidx.room.*

// Copiado de https://medium.com/@tonyowen/room-entity-annotations-379150e1ca82
@Entity(foreignKeys = arrayOf(
    ForeignKey(
        entity = AplicacionEntity::class,
        parentColumns = arrayOf("idAplicacion"),
        childColumns = arrayOf("idAplicacion"),
        onDelete = ForeignKey.NO_ACTION),
    ForeignKey(
        entity = UsuarioEntity::class,
        parentColumns = arrayOf("idUsuario"),
        childColumns = arrayOf("idUsuario"),
        onDelete = ForeignKey.NO_ACTION
    )),
    indices = [
        Index("idAplicacion"),
        Index("idUsuario")
    ]
)
data class PuntuacionEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idPuntuacion") val idPuntuacion : Int,
    @ColumnInfo(name = "puntuacion") val puntuacion : Int,
    @ColumnInfo(name = "cheated",typeAffinity = ColumnInfo.INTEGER ) val cheated : Boolean,
    @ColumnInfo(name = "idAplicacion") val idAplicacion : Int,
    @ColumnInfo(name = "idUsuario") val idUsuario : Int
)