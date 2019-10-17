package com.example.quizappplus.DB.Entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Configuracion")
data class ConfiguracionEntity (
    @PrimaryKey @ColumnInfo(name = "idConfiguracion") val idConfiguracion : Int,
    @ColumnInfo(name = "categoriasUsadas") var categoriasUsadas : String,
    @ColumnInfo(name = "numeroPreguntas") var numeroPreguntas : Int,
    @ColumnInfo(name = "dificultad") var dificultad : String,
    @ColumnInfo(name = "pistasEnabled", typeAffinity = ColumnInfo.INTEGER) var pistasEnabled : Boolean,
    @ColumnInfo(name = "numeroPistas") var numeroPistas : Int
)
