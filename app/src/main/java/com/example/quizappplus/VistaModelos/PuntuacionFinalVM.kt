package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.DB.DAOs.PuntuacionDao
import com.example.quizappplus.DB.DAOs.ReportesDao
import com.example.quizappplus.Modelos.Usuario

class PuntuacionFinalVM:ViewModel() {
    private var listaPuntuaciones:List<ReportesDao.PuntuacionReport> = listOf()
    private var flagVentanaAbierta=false
    private lateinit var usuarioActual: Usuario

    val ListaPuntuaciones get() = listaPuntuaciones
    val JugadorActual get() = usuarioActual

    fun SetJugadores(db:AppDatabase){
        if (!flagVentanaAbierta) {
            this.listaPuntuaciones = db.getReportesDao().GetPuntuacionesReport()
            VentanaAbierta()
        }
    }
    fun SetJugadorActual(usuario: Usuario){
        usuarioActual=usuario
    }

    fun VentanaAbierta() {flagVentanaAbierta=true}
}