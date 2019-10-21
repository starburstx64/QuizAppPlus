package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.DB.DAOs.PuntuacionDao
import com.example.quizappplus.Modelos.Usuario

class PuntuacionesVM:ViewModel() {
    private var listaPuntuaciones:List<PuntuacionDao.PuntuacionReport> = listOf()

    private var flagVentanaAbierta = false

    val ListaPuntuaciones get() = listaPuntuaciones

    /**
     * @brief Obtinene el score de la base de datos y lo asigna en la propiedad listaJugadores
     */
    fun Inicializar() {

    }

    fun SetJugadores(db: AppDatabase){
        if (!flagVentanaAbierta) {
            this.listaPuntuaciones = db.getPuntuacionDao().GetPuntuacionesReport()
            VentanaAbierta()
        }
    }

    fun VentanaAbierta() {flagVentanaAbierta=true}
}