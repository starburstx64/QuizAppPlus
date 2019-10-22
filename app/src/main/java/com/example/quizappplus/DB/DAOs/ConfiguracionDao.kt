package com.example.quizappplus.DB.DAOs

import androidx.room.*
import com.example.quizappplus.DB.Entidades.ConfiguracionEntity

@Dao
interface ConfiguracionDao {

    @Query("SELECT * FROM configuracion WHERE idConfiguracion=:idConfiguracion")
    fun GetConfiguraciones(idConfiguracion:Int):ConfiguracionEntity

    @Insert
    fun CrearNuevaConfiguracion(configuracion:ConfiguracionEntity):Long

    @Update
    fun UpdateConfiguraciones(configuraciones:ConfiguracionEntity)

    @Query("DELETE FROM configuracion WHERE idConfiguracion=:idConfiguracion")
    fun DeleteConfiguracion(idConfiguracion: Int)

    @Delete
    fun DeleteConfiguracion(configuracion: ConfiguracionEntity)


}