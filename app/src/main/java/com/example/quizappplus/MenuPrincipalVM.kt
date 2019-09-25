package com.example.quizappplus

import androidx.lifecycle.ViewModel

class MenuPrincipalVM:ViewModel(){
    var configuraciones:ConfiguracionesVM = ConfiguracionesVM()
    var mejoresPuntajes:ArrayList<Jugador> = arrayListOf()
    private var flagInicioJuego:Boolean = false


    val FlagInicio get() = flagInicioJuego

    fun SetFlagInicioJuego()
    {
        flagInicioJuego = true
    }
}