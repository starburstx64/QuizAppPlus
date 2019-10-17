package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.Modelos.Configuraciones
import com.example.quizappplus.Modelos.Jugador
import com.example.quizappplus.VistaModelos.ConfiguracionesVM

class MenuPrincipalVM:ViewModel(){
    //Inicializamos las configuraciones con sus valores base
    var configuraciones: Configuraciones =
        Configuraciones()

    //Inicializamos los puntajes para pasarlos por toda la aplicación
    var mejoresPuntajes:ArrayList<Jugador> = arrayListOf()

    //Esta bandera se cambia a true cuando el juego comienza
    //con eso no reiniciamos las configuraciones ni los puntajes cada que se gira la pantalla o algo asi
    private var flagInicioJuego:Boolean = false

    //propiedad para obtener el valor de la bandera
    val FlagInicio get() = flagInicioJuego

    //Solo con este metodo podemos cambiar el estado de la bandera a true
    fun SetFlagInicioJuego()
    {
        flagInicioJuego = true
    }

    fun InicializarJuego()
    {
        if (FlagInicio == false){
            SetFlagInicioJuego()
            mejoresPuntajes.add(
                Jugador(
                    "Jose",
                    1000,
                    false,
                    6
                )
            )
            mejoresPuntajes.add(
                Jugador(
                    "Pedro",
                    950,
                    true,
                    5
                )
            )
        }
    }

    /**
     * @brief Returna true si hay algun juego guardado
     */
    fun existeJuegoGuardado() : Boolean {
        return true
    }

    /**
     * @brief Elimina el juego guardado anteriormente si existe
     */
    fun eliminarJuegoGuardado() {

    }
}