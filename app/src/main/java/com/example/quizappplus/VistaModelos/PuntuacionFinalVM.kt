package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.Modelos.Jugador

class PuntuacionFinalVM:ViewModel() {
    private var listaJugadores:List<Jugador> = listOf()
    private var flagVentanaAbierta=false
    private lateinit var jugadorActual: Jugador

    val ListaJugadores get() = listaJugadores
    val JugadorActual get() = jugadorActual

    fun SetJugadores(listaJugadores:List<Jugador>){
        if (!flagVentanaAbierta) {
            this.listaJugadores = listaJugadores
            VentanaAbierta()
        }
    }
    fun SetJugadorActual(jugador: Jugador){
        jugadorActual=jugador
    }

    fun VentanaAbierta() {flagVentanaAbierta=true}
}