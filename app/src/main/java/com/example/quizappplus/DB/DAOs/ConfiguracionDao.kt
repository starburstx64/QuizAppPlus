package com.example.quizappplus.DB.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.quizappplus.DB.Entidades.ConfiguracionEntity

@Dao
interface ConfiguracionDao {

    @Query("SELECT * FROM configuracion WHERE idConfiguracion=:idConfiguracion")
    fun GetConfiguraciones(idConfiguracion:Int):ConfiguracionEntity

    @Insert
    fun CrearNuevaConfiguracion(configuracion:ConfiguracionEntity):Long

    @Update
    fun UpdateConfiguraciones(configuraciones:ConfiguracionEntity)


}