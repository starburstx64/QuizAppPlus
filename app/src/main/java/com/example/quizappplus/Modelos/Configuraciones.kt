package com.example.quizappplus.Modelos

import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.DB.Entidades.ConfiguracionEntity
import com.example.quizappplus.R
import java.io.Serializable

class Configuraciones:Serializable {

    //son las categorias que existen en la aplicación
    lateinit var usedCategoriesIds:MutableList<Int>

    //El numero de preguntas que esta seleccionado
    var numPregunta:Int = 5

    //La dificultad seleccionada, 1 es facil, 2 medio y 3 dificil
    var dificultad:Int = 2

    //variable que indica si se pueden usar pistas o no
    var pistas: Boolean = false

    //Variable que indica el numero de pistas seleccionadas
    var numPistas:Int = 2

    /**
     * @brief Retorna el numero de categorias que exiten en la base de datos
     */
    fun getCategoriesCount() : Int {
        return 6
    }

    fun GetUsedCategoriesIds(categoriasUsadas:String):MutableList<Int>
    {
        var usedCategories = mutableListOf<Int>()
        var number=""
        for (i in 0 until categoriasUsadas.length)
        {
            if (categoriasUsadas[i]=='/')
            {
                usedCategories.add(number.toInt())
                number=""
            }
            else
            {
                number+=categoriasUsadas[i]
            }
        }
        return usedCategories
    }

    private fun SetUsedCategoriesIds():String
    {
        var categoriasUsadas:String = ""
        for(i in 0 until usedCategoriesIds.size)
        {
            categoriasUsadas += usedCategoriesIds[i]
            categoriasUsadas+= '/'
        }
        return categoriasUsadas
    }

    fun UpdateConfiguraciones(db:AppDatabase,idUsuario: Int)
    {
        val idConfiguraciones = db.getUsuarioDao().GetIdConfiguraciones(idUsuario)
        val configs = db.getConfiguracionDao().GetConfiguraciones(idConfiguraciones)

        configs.categoriasUsadas= SetUsedCategoriesIds()
        configs.numeroPreguntas=numPregunta
        configs.dificultad=dificultad
        configs.pistasEnabled=pistas
        configs.numeroPistas=numPistas

        db.getConfiguracionDao().UpdateConfiguraciones(configs)
    }

    companion object{

        fun InsertarConfiguracion(db:AppDatabase):Int
        {
            var toInsert = ConfiguracionEntity(categoriasUsadas = "1/2/3/",numeroPreguntas = 5,dificultad = 2,pistasEnabled = false,numeroPistas = 2)
            var idConfig = db.getConfiguracionDao().CrearNuevaConfiguracion(toInsert)
            return idConfig.toInt()
        }

        fun GetConfiguraciones(db:AppDatabase,idUsuario:Int):Configuraciones
        {
            val configActuales = Configuraciones()
            val idConfiguraciones = db.getUsuarioDao().GetIdConfiguraciones(idUsuario)
            val configs = db.getConfiguracionDao().GetConfiguraciones(idConfiguraciones)

            configActuales.usedCategoriesIds = configActuales.GetUsedCategoriesIds(configs.categoriasUsadas)
            configActuales.numPregunta=configs.numeroPreguntas
            configActuales.dificultad=configs.dificultad
            configActuales.pistas=configs.pistasEnabled
            configActuales.numPistas=configs.numeroPistas
            return configActuales
        }

    }
}