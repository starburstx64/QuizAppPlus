package com.example.quizappplus.DB.Entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Configuracion")
data class ConfiguracionEntity (
    @PrimaryKey @ColumnInfo(name = "idConfiguracion") val idConfiguracion : Int,
    @ColumnInfo(name = "categoriasUsadas") val categoriasUsadas : String,
    @ColumnInfo(name = "numeroPreguntas") val numeroPreguntas : Int,
    @ColumnInfo(name = "dificultad") val dificultad : String,
    @ColumnInfo(name = "pistasEnabled") val pistasEnabled : Boolean,
    @ColumnInfo(name = "numeroPistas") val numeroPistas : Int
)
