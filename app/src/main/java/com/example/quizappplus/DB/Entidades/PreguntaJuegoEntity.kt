package com.example.quizappplus.DB.Entidades

import androidx.room.*

@Entity(tableName = "PreguntaJuego",
    primaryKeys = arrayOf("idJuego","idPregunta"),
    foreignKeys = arrayOf(
        ForeignKey(
            entity = JuegoEntity::class,
            parentColumns = arrayOf("idJuego"),
            childColumns = arrayOf("idJuego"),
            onDelete = ForeignKey.NO_ACTION),
        ForeignKey(
            entity = PreguntaEntity::class,
            parentColumns = arrayOf("idPregunta"),
            childColumns = arrayOf("idPregunta"),
            onDelete = ForeignKey.NO_ACTION)
    ),

    indices = [Index("idJuego"), Index("idPregunta")]
)
data class PreguntaJuegoEntity (
    @ColumnInfo(name = "idJuego") val idJuego:Int,
    @ColumnInfo(name = "idPregunta") val idPregunta:Int,
    @ColumnInfo(name = "contestada",typeAffinity = ColumnInfo.INTEGER) var contestada:Boolean = false,
    @ColumnInfo(name = "correcta",typeAffinity = ColumnInfo.INTEGER) var correcta:Boolean = false,
    @ColumnInfo(name = "ordenEnJuego") val ordenEnJuego:Int,
    @ColumnInfo(name = "ordenOpciones") val ordenOpciones:Int,
    @ColumnInfo(name = "optionsCheated") var optionsCheated:String,
    @ColumnInfo(name = "cheated", typeAffinity = ColumnInfo.INTEGER) var cheated:Boolean
)