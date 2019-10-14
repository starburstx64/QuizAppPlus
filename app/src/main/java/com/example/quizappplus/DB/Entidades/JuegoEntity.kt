package com.example.quizappplus.DB.Entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(entity = UsuarioEntity::class,
    parentColumns = arrayOf("idUsuario"),
    childColumns = arrayOf("idUsuario")))
)
data class JuegoEntity(
    @PrimaryKey(autoGenerate = true)
    var idJuego: Int,
    var idUsuario : Int,
    var estatusJuego : String,
    var cheated : Boolean
)