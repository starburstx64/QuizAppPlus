package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
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

    /**
     * @brief Obtiene las configuraciones de la base de datos y establece la propiedad configuraciones
     */
    fun Inicializar() {
        configuraciones = Configuraciones()

        // acceder a la bd y saldnalsdkn
    }

    fun GetCategoriasActivas():Int
    {
        var contCategorias = 0
        for (i in configuraciones.categorias)
        {
            contCategorias=
                if (i.seleccionada)
                    contCategorias+1
                else
                    contCategorias
        }
        return contCategorias
    }

    /**
     * @brief Guarda las configuraciones en la base de datos
     */
    fun guardarConfiguraciones() {

    }
}