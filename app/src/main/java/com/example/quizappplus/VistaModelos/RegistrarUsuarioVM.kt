package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.DB.Entidades.UsuarioEntity
import com.example.quizappplus.Modelos.Usuario

class RegistrarUsuarioVM : ViewModel() {

    private lateinit var database: AppDatabase

    var selectedWaifu = 0
    var userNameText = ""
    var userPasswordText = ""
    var editarUsuarioActivity = false

    fun inicializar(database: AppDatabase) {
        this.database = database
    }

    fun tryRegistrarUsuario() : Boolean {
        val usuarioRepetido = database.getUsuarioDao().getUsuarioByName(userNameText)

        if (usuarioRepetido != null) {

            // Sorry
            if (usuarioRepetido.idUsuario != database.getAplicacionDao().getIdUsuarioActivo()!!) {
                return false
            }
        }

        val toInsert = Usuario(-1, userNameText, selectedWaifu, userNameText, userPasswordText, 0, 0)

        if (editarUsuarioActivity) {
            // Sorry x 2
            toInsert.id = database.getAplicacionDao().getIdUsuarioActivo()!!
            Usuario.actualizarUsuario(database, toInsert)
        }

        else {
            Usuario.AgregarUsuario(database, toInsert)
        }

        return true
    }

    /**
     * @brief Si el usuario ya esta registrado esta funcion se llama para actualizar el modelo con sus datos
     */
    fun actualizarDatosUsuarioActivo() {
        val user = database.getUsuarioDao().getUsuarioById(database.getAplicacionDao().getIdUsuarioActivo()!!)

        selectedWaifu = user.imagenAvatar!!
        userNameText = user.userName
        userPasswordText = user.contraseña

        editarUsuarioActivity = true
    }
}