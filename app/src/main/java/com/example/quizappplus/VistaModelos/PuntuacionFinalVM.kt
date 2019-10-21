package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.DB.DAOs.PuntuacionDao
import com.example.quizappplus.DB.DAOs.ReportesDao
import com.example.quizappplus.Modelos.Usuario

class PuntuacionFinalVM:ViewModel() {
    private var listaPuntuaciones:List<ReportesDao.PuntuacionReport> = listOf()
    private var flagVentanaAbierta=false
    var PuntuacionUsuario:Int = 0
    var Acuracy:Double = 0.0

    val ListaPuntuaciones get() = listaPuntuaciones

    fun SetJugadores(db:AppDatabase){
        if (!flagVentanaAbierta) {
            this.listaPuntuaciones = db.getReportesDao().GetPuntuacionesReport()
            VentanaAbierta()
        }
    }

    fun VentanaAbierta() {flagVentanaAbierta=true}
}