package com.example.quizappplus

import androidx.lifecycle.ViewModel

class PuntuacionFinalVM:ViewModel() {
    private var listaJugadores:List<Jugador> = listOf()
    private var flagVentanaAbierta=false

    val ListaJugadores get() = listaJugadores

    fun SetJugadores(listaJugadores:List<Jugador>){
        if (!flagVentanaAbierta) {
            this.listaJugadores = listaJugadores
            VentanaAbierta()
        }
    }

    fun VentanaAbierta() {flagVentanaAbierta=true}
}