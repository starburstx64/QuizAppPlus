package com.example.quizappplus.DB.Entidades

import androidx.room.*

@Entity(tableName = "Juego",
    foreignKeys = arrayOf(ForeignKey(entity = UsuarioEntity::class,
    parentColumns = arrayOf("idUsuario"),
    childColumns = arrayOf("idUsuario"))),

    indices = [Index("idUsuario")]
)
data class JuegoEntity(
    @PrimaryKey @ColumnInfo(name = "idJuego") val idJuego: Int,
    @ColumnInfo(name = "idUsuario") val idUsuario : Int,
    @ColumnInfo(name = "statusJuego") var estatusJuego : Int,
    @ColumnInfo(name = "cheated")var cheated : Boolean
)