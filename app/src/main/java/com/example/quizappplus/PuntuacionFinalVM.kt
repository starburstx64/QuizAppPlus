package com.example.quizappplus

import androidx.lifecycle.ViewModel

class PuntuacionFinalVM:ViewModel() {
    private var listaJugadores:List<Jugador> = listOf()
    private var flagVentanaAbierta=false
    private lateinit var jugadorActual:Jugador

    val ListaJugadores get() = listaJugadores
    val JugadorActual get() = jugadorActual

    fun SetJugadores(listaJugadores:List<Jugador>){
        if (!flagVentanaAbierta) {
            this.listaJugadores = listaJugadores
            VentanaAbierta()
        }
    }
    fun SetJugadorActual(jugador:Jugador){
        jugadorActual=jugador
    }

    fun VentanaAbierta() {flagVentanaAbierta=true}
}