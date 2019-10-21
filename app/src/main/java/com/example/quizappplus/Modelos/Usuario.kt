package com.example.quizappplus.Modelos

import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.DB.Entidades.JuegoEntity
import java.io.Serializable

data class Usuario(var id:Int,var nombre:String,var idImagenAvatar:Int,var userName:String,var contraseña:String,val idConfiguracion:Int = 0 ,val idAplicacion:Int = 0):Serializable{

    companion object{
        fun GetGameStatus(db:AppDatabase,idUsuario:Int):Boolean{
            val juegoActual:JuegoEntity = db.getJuegoDao().GetJuego(idUsuario)
            return juegoActual.estatusJuego==1
        }

        fun GetGameData(db:AppDatabase,idUsuario: Int):JuegoEntity
        {
            var datosJuego:JuegoEntity = db.getJuegoDao().GetJuego(idUsuario)
            return datosJuego
        }

        fun AgregarUsuario(db:AppDatabase,usuario:Usuario)
        {
            var idConfiguracion = Configuraciones.InsertarConfiguracion(db)
            var idUsuario = db.getUsuarioDao().insertUsuario(idConfiguracion,usuario.idImagenAvatar,usuario.userName,usuario.contraseña)

            var juegotoToInsert = JuegoEntity(idUsuario = idUsuario.toInt(),estatusJuego = 0,numPistas = 0,cheated = false)
            db.getJuegoDao().CreateGameForUser(juegotoToInsert)


        }

        fun actualizarUsuario(db: AppDatabase, usuario: Usuario) {
            db.getUsuarioDao().actualizarUsuario(usuario.userName, usuario.contraseña, usuario.idImagenAvatar)
        }

        fun GetActiveUserId(db:AppDatabase):Int
        {
            return db.getAplicacionDao().getIdUsuarioActivo()!!
        }

        fun FinishGame(db: AppDatabase,idUsuario: Int)
        {
            val juegoActual:JuegoEntity = db.getJuegoDao().GetJuego(idUsuario)
            juegoActual.estatusJuego = 0
            juegoActual.numPistas = 0
            juegoActual.cheated = false
            db.getJuegoDao().UpdateJuego(juegoActual)
            db.getPreguntaJuegoDao().DeleteGameQuestions(juegoActual.idJuego!!)
        }

        fun StartGame(db:AppDatabase,idUsuario: Int)
        {
            db.getJuegoDao().StartGame(idUsuario)
        }
    }
}