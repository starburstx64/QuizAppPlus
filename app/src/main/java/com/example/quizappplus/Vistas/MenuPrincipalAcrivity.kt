package com.example.quizappplus.Vistas

import android.app.Activity
import android.app.AlertDialog
import com.example.quizappplus.Modelos.Configuraciones
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import com.example.quizappplus.*
import com.example.quizappplus.Modelos.Jugador
import com.example.quizappplus.VistaModelos.MenuPrincipalVM
import kotlin.math.log

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

        //Cuando inicia la actividad se inicializan las variables importantes
        model.InicializarJuego()

        //Cuando se le da click al boton de jugar
        btnJuego.setOnClickListener {

            val intent = Intent(this, PreguntasActivity::class.java)

            if (model.existeJuegoGuardado()) {

                // Mostramos un alert dialog
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Confirmar")
                builder.setMessage("¿Desea Continuar con el juego anterior?")

                builder.setPositiveButton("Si") { _, _ ->
                    // Iniciamos el activity sin eliminar el juego
                    startActivity(intent)
                }

                builder.setNegativeButton("No") {_, _ ->
                    model.eliminarJuegoGuardado()
                    startActivity(intent)
                }

                builder.show()
            }

            else {
                startActivity(intent)
            }
        }

        //Cuando se le da click al boton de puntuaciones
        btnPuntuacion.setOnClickListener {
            val intent = Intent(this, PuntuacionActivity::class.java)
            startActivity(intent)
        }

        //Cuando se le da click al boton de configuraciones
        btnOpciones.setOnClickListener {

            val intent = Intent(this, ConfiguracionesActivity::class.java)

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
