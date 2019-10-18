package com.example.quizappplus.Modelos

import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.R
import java.io.Serializable

class Configuraciones:Serializable {

    //son las categorias que existen en la aplicación
    lateinit var usedCategoriesIds:List<Int>

    //El numero de preguntas que esta seleccionado
    var numPregunta:Int = 5

    //La dificultad seleccionada, 1 es facil, 2 medio y 3 dificil
    var dificultad:Int = 2

    //variable que indica si se pueden usar pistas o no
    var pistas: Boolean = false

    //Variable que indica el numero de pistas seleccionadas
    var numPistas:Int = 2

    fun GetUsedCategoriesIds(categoriasUsadas:String):List<Int>
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

    companion object{

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