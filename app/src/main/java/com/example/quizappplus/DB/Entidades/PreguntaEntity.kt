package com.example.quizappplus.DB.Entidades

import androidx.room.*

@Entity(tableName = "Pregunta",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = CategoriaEntity::class,
            parentColumns = arrayOf("idCategoria"),
            childColumns = arrayOf("idCategoria"),
            onDelete = ForeignKey.NO_ACTION
        )
    ),
    indices = [Index("idCategoria")]
)
data class PreguntaEntity (
    @PrimaryKey @ColumnInfo (name = "idPregunta") val idPregunta : Int,
    @ColumnInfo (name = "idCategoria") val idCategoria : Int,
    @ColumnInfo (name = "texto") val texto : String

)
