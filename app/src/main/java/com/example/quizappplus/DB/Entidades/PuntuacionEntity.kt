package com.example.quizappplus.DB.Entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Copiado de https://medium.com/@tonyowen/room-entity-annotations-379150e1ca82
@Entity(foreignKeys = arrayOf(
                        ForeignKey(
                            entity = AplicacionEntity::class,
                            parentColumns = arrayOf("idAplicacion"),
                            childColumns = arrayOf("idAplicacion"),
                            onDelete = ForeignKey.NO_ACTION)),
    indices = [Index("idAplicacion")]
)
data class PuntuacionEntity(
    @PrimaryKey(autoGenerate = true) var idPuntuacion : Int,
    val puntuacion : Int,
    val cheated : Boolean,
    val idAplicacion : Int,
    val idUsuario : Int
)