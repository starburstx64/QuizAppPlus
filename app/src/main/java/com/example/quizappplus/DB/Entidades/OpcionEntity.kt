package com.example.quizappplus.DB.Entidades

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(
                        ForeignKey(
                            entity = PreguntaEntity::class,
                            parentColumns = arrayOf("idPregunta"),
                            childColumns = arrayOf("idPregunta"),
                            onDelete = ForeignKey.NO_ACTION)),
                            indices = [Index("idPregunta")]
)
data class OpcionEntity (
    @PrimaryKey(autoGenerate = true) val idOpcion : Int,
    val texto : String,
    val isCorrect : Boolean,
    val idPregunta : Int
)