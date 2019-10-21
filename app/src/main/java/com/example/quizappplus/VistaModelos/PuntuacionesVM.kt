package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.DB.DAOs.PuntuacionDao
import com.example.quizappplus.DB.DAOs.ReportesDao
import com.example.quizappplus.Modelos.Usuario

class PuntuacionesVM:ViewModel() {
    private var listaPuntuaciones:List<ReportesDao.PuntuacionReport> = listOf()

    private var flagVentanaAbierta = false

    val ListaPuntuaciones get() = listaPuntuaciones

    /**
     * @brief Obtinene el score de la base de datos y lo asigna en la propiedad listaJugadores
     */
    fun Inicializar() {

    }

    fun SetJugadores(db: AppDatabase){
        if (!flagVentanaAbierta) {
            this.listaPuntuaciones = db.getReportesDao().GetPuntuacionesReport()
            VentanaAbierta()
        }
    }

    fun VentanaAbierta() {flagVentanaAbierta=true}
}