package com.example.quizappplus.DB.Entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(entity = UsuarioEntity::class,
    parentColumns = arrayOf("idUsuario"),
    childColumns = arrayOf("idUsuarioActivo")))
)
data class AplicacionEntity(
    @PrimaryKey(autoGenerate = true)
    var idApliacion : Int,
    var idUsuarioActivo : Int
)