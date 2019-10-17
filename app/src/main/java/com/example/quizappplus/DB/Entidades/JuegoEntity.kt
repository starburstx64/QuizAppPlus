package com.example.quizappplus.DB.Entidades

import androidx.room.*

@Entity(
    tableName = "Juego",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = UsuarioEntity::class,
            parentColumns = arrayOf("idUsuario"),
            childColumns = arrayOf("idUsuario"),
            onDelete = ForeignKey.NO_ACTION
        )
    ),
    indices = [Index("idUsuario")]
)
data class JuegoEntity(
    @PrimaryKey @ColumnInfo(name = "idJuego") val idJuego: Int,
    @ColumnInfo(name = "idUsuario") val idUsuario: Int,
    @ColumnInfo(name = "estatusJuego") var estatusJuego: Int,
    @ColumnInfo(name = "numPistas") var numPistas: Int,
    @ColumnInfo(name = "cheated", typeAffinity = ColumnInfo.INTEGER) var cheated: Boolean
)