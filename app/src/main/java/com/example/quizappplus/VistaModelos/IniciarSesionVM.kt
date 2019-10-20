package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.DB.AppDatabase

class IniciarSesionVM : ViewModel() {

    var userNameText : String = ""
    var userPasswordText: String = ""

    private lateinit var database: AppDatabase
    private var idUsuarioActivo : Int? = null

    fun inicializar(database: AppDatabase) {
        this.database = database
    }

    /**
     * @brief Retorna true si userName y userPassword son validos
     */
    fun tryLogin(userName : String, userPassword : String) : Boolean {

        val usuario = database.getUsuarioDao().getUsuario(userName, userPassword)

        if (usuario != null) {
            idUsuarioActivo = usuario.idUsuario
            database.getAplicacionDao().setIdUsuarioActivo(getIdUsuarioActivo())

            return true
        }

        return false
    }

    fun getIdUsuarioActivo() : Int {
        return idUsuarioActivo!!
    }
}