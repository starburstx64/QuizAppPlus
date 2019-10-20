package com.example.quizappplus.Vistas

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import com.example.quizappplus.*
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.DB.Entidades.PuntuacionEntity
import com.example.quizappplus.Modelos.Configuraciones
import com.example.quizappplus.Modelos.Usuario
import com.example.quizappplus.VistaModelos.MenuPrincipalVM
import com.facebook.stetho.Stetho

class MenuPrincipalAcrivity : AppCompatActivity() {

    //Hacemos referencia a los controles de la vista
    //region controlesVista
    private lateinit var btnJuego: Button
    private lateinit var btnOpciones: Button
    private lateinit var btnPuntuacion: Button
    //endregion
    //Este es el ViewModel del menu principal
    private val model by lazy { ViewModelProviders.of(this)[MenuPrincipalVM::class.java] }

    //region estadosAndroid
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        //region inicializarControles
        btnJuego = findViewById(R.id.juego_button)
        btnOpciones = findViewById(R.id.opciones_button)
        btnPuntuacion = findViewById(R.id.puntuacion_button)
        //endregion

        Stetho.initializeWithDefaults(this)

        val db = AppDatabase.getAppDatabase(this)
        val aplicacion = db.getAplicacionDao()
        
        //Cuando inicia la actividad se inicializan las variables importantes
        model.InicializarJuego(db)

        // Obtener el id del usuario logeado
        val idUsuarioActivo = aplicacion.getIdUsuarioActivo()
        if (idUsuarioActivo == null) {
            // Abrir login Activity
            val loginIntent = Intent(this, IniciarSesionActivity::class.java)
            startActivity(loginIntent)
        }

        else {
            model.setIdUsuarioActivo(idUsuarioActivo)
        }

        //Cuando se le da click al boton de jugar
        btnJuego.setOnClickListener {

            // Confiamos en que Login Activity guarde el id del usuario activo en la bd
            model.setIdUsuarioActivo(aplicacion.getIdUsuarioActivo()!!)

            val existeJuegoGuardado = model.existeJuegoGuardado()

            val intent = Intent(this, PreguntasActivity::class.java)
            intent.putExtra("idUsuarioActivo", model.getIdUsurioActivo())

            if (existeJuegoGuardado) {

                // Mostramos un alert dialog
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Confirmar")
                builder.setMessage("¿Desea Continuar con el juego anterior?")

                builder.setPositiveButton("Si") { _, _ ->
                    // Iniciamos el activity sin eliminar el juego
                    intent.putExtra("existeJuegoGuardado", true)
                    startActivity(intent)
                }

                builder.setNegativeButton("No") {_, _ ->
                    model.eliminarJuegoGuardado()
                    intent.putExtra("existeJuegoGuardado", false)
                    startActivity(intent)
                }

                builder.show()
            }

            else {
                intent.putExtra("existeJuegoGuardado", false)
                startActivity(intent)
            }
        }

        //Cuando se le da click al boton de puntuaciones
        btnPuntuacion.setOnClickListener {
            // Confiamos en que Login Activity guarde el id del usuario activo en la bd
            model.setIdUsuarioActivo(aplicacion.getIdUsuarioActivo()!!)

            val intent = Intent(this, PuntuacionActivity::class.java)
            startActivity(intent)
        }

        //Cuando se le da click al boton de configuraciones
        btnOpciones.setOnClickListener {

            // Confiamos en que Login Activity guarde el id del usuario activo en la bd
            model.setIdUsuarioActivo(aplicacion.getIdUsuarioActivo()!!)

            val intent = Intent(this, ConfiguracionesActivity::class.java)
            intent.putExtra("idUsuarioActivo", model.getIdUsurioActivo())

            if (model.existeJuegoGuardado()) {

                // Mostramos un alert dialog
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Confirmar")
                builder.setMessage("Modificar las configuraciones hará que los juegos guardados sean eliminados. ¿Desea continuar?")

                builder.setPositiveButton("Si") { _,_ ->
                    model.eliminarJuegoGuardado()
                    startActivity(intent)
                }

                builder.setNegativeButton("No") { _, _ ->
                    // nothing
                }

                builder.show()
            }

            else {
                startActivity(intent)
            }
        }

    }
    //endregion
}
