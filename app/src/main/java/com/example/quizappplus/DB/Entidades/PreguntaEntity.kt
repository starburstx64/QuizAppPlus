package com.example.quizappplus.DB.Entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "Pregunta",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = CategoriaEntity::class,
            parentColumns = arrayOf("idCategoria"),
            childColumns = arrayOf("idCategoria"),
            onDelete = ForeignKey.NO_ACTION
        )
    ))
class PreguntaEntity (
    @PrimaryKey @ColumnInfo (name = "idPregunta") val idPregunta : Int,
    @ColumnInfo (name = "idCategoria") val idCategoria : Int,
    @ColumnInfo (name = "texto") val texto : String

)
