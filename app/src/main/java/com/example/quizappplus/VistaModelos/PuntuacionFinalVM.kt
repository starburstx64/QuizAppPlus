package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.Modelos.Usuario

class PuntuacionFinalVM:ViewModel() {
    private var listaJugadores:List<Usuario> = listOf()
    private var flagVentanaAbierta=false
    private lateinit var usuarioActual: Usuario

    val ListaJugadores get() = listaJugadores
    val JugadorActual get() = usuarioActual

    fun SetJugadores(listaJugadores:List<Usuario>){
        if (!flagVentanaAbierta) {
            this.listaJugadores = listaJugadores
            VentanaAbierta()
        }
    }
    fun SetJugadorActual(usuario: Usuario){
        usuarioActual=usuario
    }

    fun VentanaAbierta() {flagVentanaAbierta=true}
}