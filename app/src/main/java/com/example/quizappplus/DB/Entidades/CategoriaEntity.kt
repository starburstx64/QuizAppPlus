package com.example.quizappplus.DB.Entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Categoria")
data class CategoriaEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "idCategoria") val idCategoria : Int? = null,
    @ColumnInfo(name = "nombre") val nombre : String
)
