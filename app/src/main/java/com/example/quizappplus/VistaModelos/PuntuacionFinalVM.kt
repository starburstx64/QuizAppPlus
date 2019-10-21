package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.DB.DAOs.PuntuacionDao
import com.example.quizappplus.Modelos.Usuario

class PuntuacionFinalVM:ViewModel() {
    private var listaPuntuaciones:List<PuntuacionDao.PuntuacionReport> = listOf()
    private var flagVentanaAbierta=false
    private lateinit var usuarioActual: Usuario

    val ListaPuntuaciones get() = listaPuntuaciones
    val JugadorActual get() = usuarioActual

    fun SetJugadores(db:AppDatabase){
        if (!flagVentanaAbierta) {
            this.listaPuntuaciones = db.getPuntuacionDao().GetPuntuacionesReport()
            VentanaAbierta()
        }
    }
    fun SetJugadorActual(usuario: Usuario){
        usuarioActual=usuario
    }

    fun VentanaAbierta() {flagVentanaAbierta=true}
}