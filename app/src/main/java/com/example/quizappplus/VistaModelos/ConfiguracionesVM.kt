package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.Modelos.Categoria
import com.example.quizappplus.Modelos.Configuraciones
import com.example.quizappplus.Modelos.Opcion
import com.example.quizappplus.Modelos.Pregunta
import com.example.quizappplus.R
import java.io.Serializable

class ConfiguracionesVM : ViewModel(), Serializable {

    //El objeto configuraciones que recibe y va a utilizar
    lateinit var configuraciones: Configuraciones

    private var flagActivityStarted = false
    var numCategoriesSelected = 0

    val FlagActivityStarted get() = flagActivityStarted

    private lateinit var database: AppDatabase
    private var idUsuarioActivo: Int = 0

    /**
     * @brief Obtiene las configuraciones de la base de datos y establece la propiedad configuraciones
     */
    fun Inicializar(database : AppDatabase, idUsuarioActivo : Int) {

        this.database = database
        this.idUsuarioActivo = idUsuarioActivo

        configuraciones = Configuraciones.GetConfiguraciones(database, idUsuarioActivo)
        numCategoriesSelected = configuraciones.usedCategoriesIds.size
    }

    fun GetCategoriasActivas():Int
    {
        return configuraciones.usedCategoriesIds.size
    }

    /**
     * @brief Guarda las configuraciones en la base de datos
     */
    fun guardarConfiguraciones() {
        configuraciones.UpdateConfiguraciones(database, idUsuarioActivo)
    }
}