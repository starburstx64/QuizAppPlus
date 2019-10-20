package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Query
import com.example.quizappplus.DB.Entidades.OpcionEntity

@Dao
interface OpcionDao {


     @Query("SELECT * FROM opcion WHERE idPregunta=:idPregunta")
    fun GetQuestionOptions(idPregunta:Int):List<OpcionEntity>

}