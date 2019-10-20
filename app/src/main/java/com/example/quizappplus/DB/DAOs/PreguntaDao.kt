package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Query
import com.example.quizappplus.DB.Entidades.PreguntaEntity

@Dao
interface PreguntaDao {

    @Query("SELECT * FROM pregunta WHERE idCategoria IN (:categorias)")
    fun SelectQuestionsFromCategories(categorias: List<Int>):List<PreguntaEntity>

}