package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.DB.AppDatabase

class PerfilUsuarioVM : ViewModel() {

    var photoid : Int? = null
    lateinit var userName : String
    lateinit var userPassword : String

    private lateinit var database: AppDatabase

    fun inicializar(appDatabase: AppDatabase) {
        database = appDatabase

        // llegados a este punto idUsuarioActivo no debe ser null
        val idUsuarioActivo = appDatabase.getAplicacionDao().getIdUsuarioActivo()!!
        val usuario = database.getUsuarioDao().getUsuarioById(idUsuarioActivo)

        photoid = usuario.imagenAvatar
        userName = usuario.userName
        userPassword = usuario.contraseña
    }
}