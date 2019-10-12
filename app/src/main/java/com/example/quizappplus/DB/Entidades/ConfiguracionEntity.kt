package com.example.quizappplus.DB.Entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Configuracion")
class ConfiguracionEntity (
    @PrimaryKey @ColumnInfo(name = "idConfiguracion") val idConfiguracion : Int,
    @ColumnInfo(name = "categoriasUsadas") val categoriasUsadas : String,
    @ColumnInfo(name = "numeroPreguntas") val numeroPreguntas : Int,
    @ColumnInfo(name = "dificultad") val dificultad : String,
    @ColumnInfo(name = "pistasEnabled") val pistasEnabled : String,
    @ColumnInfo(name = "noPistas") val noPistas : Short
)
