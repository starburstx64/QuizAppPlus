package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.Modelos.Jugador

class PuntuacionesVM:ViewModel() {
    private var listaJugadores: List<Jugador> = listOf()

    private var flagVentanaAbierta = false

    val ListaJugadores get() = listaJugadores

    /**
     * @brief Obtinene el score de la base de datos y lo asigna en la propiedad listaJugadores
     */
    fun Inicializar() {

    }

    fun SetJugadores(listaJugadores:List<Jugador>){
        if (!flagVentanaAbierta) {
            this.listaJugadores = listaJugadores
            VentanaAbierta()
        }
    }

    fun VentanaAbierta() {flagVentanaAbierta=true}
}