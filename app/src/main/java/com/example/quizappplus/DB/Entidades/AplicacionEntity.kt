package com.example.quizappplus.DB.Entidades

import androidx.room.*

@Entity(tableName = "Aplicacion",
    foreignKeys = arrayOf(ForeignKey(entity = UsuarioEntity::class,
    parentColumns = arrayOf("idUsuario"),
    childColumns = arrayOf("idUsuarioActivo"),
    onDelete = ForeignKey.NO_ACTION)),

    indices = [Index("idUsuarioActivo")]
)
data class AplicacionEntity(
    @PrimaryKey @ColumnInfo(name = "idAplicacion") val idAplicacion : Int,
    @ColumnInfo(name = "idUsuarioActivo") var idUsuarioActivo : Int?
)