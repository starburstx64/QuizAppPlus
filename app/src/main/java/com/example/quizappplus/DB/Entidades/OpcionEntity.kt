package com.example.quizappplus.DB.Entidades

import androidx.room.*

@Entity(tableName = "Opcion",
    foreignKeys = arrayOf(
                        ForeignKey(
                            entity = PreguntaEntity::class,
                            parentColumns = arrayOf("idPregunta"),
                            childColumns = arrayOf("idPregunta"),
                            onDelete = ForeignKey.NO_ACTION)),
    indices = [Index("idPregunta")]
)
data class OpcionEntity (
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name="idOpcion") val idOpcion : Int,
    @ColumnInfo(name = "texto") val texto : String,
    @ColumnInfo(name = "isCorrect") val isCorrect : Boolean,
    @ColumnInfo(name = "idPregunta")val idPregunta : Int
)