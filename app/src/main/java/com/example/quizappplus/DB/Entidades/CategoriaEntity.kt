package com.example.quizappplus.DB.Entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class CategoriaEntity(
    @PrimaryKey(autoGenerate = true)
    var idCategoria : Int,
    var nombre : String
)