package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.Modelos.Usuario

class RegistrarUsuarioVM : ViewModel() {

    private lateinit var database: AppDatabase

    var selectedWaifu = 0
    var userNameText = ""
    var userPasswordText = ""

    fun inicializar(database: AppDatabase) {
        this.database = database
    }

    fun tryRegistrarUsuario() : Boolean {
//        val usuarioRepetido = database.getUsuarioDao().getUsuarioByName(userNameText)
//
//        if (usuarioRepetido == null) {
//            return false
//        }

        return true
    }
}