package com.example.quizappplus.Vistas

import android.app.Activity
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

        //Cuanso se le da click al boton de jugar
        btnJuego.setOnClickListener {
            val intent: Intent =
                Intent(this, PreguntasActivity::class.java)         //Creamos el intent
            intent.putExtra(
                EXTRA_CONFIGURACIONES_FOR_QUESTIONS,
                model.configuraciones
            )           //Le metemos un objeto tipo configuraciones
            intent.putExtra(
                EXTRA_PUNTUACIONES_LIST_FORQUESTIONS,
                model.mejoresPuntajes
            )  //Le metemos la lista de los mejores puntajes
            startActivityForResult(
                intent,
                PREGUNTAS_REQUEST_CODE
            )    //Esperamos su respuesta, que en este caso es la lista modificada
        }
        //Cuando se le da click al boton de puntuaciones
        btnPuntuacion.setOnClickListener {
            val intent: Intent =
                Intent(this, PuntuacionActivity::class.java)     //Se crea el intent
            intent.putExtra(
                EXTRA_MEJORES_PUNTUACIONES,
                model.mejoresPuntajes
            )               //Se le pasa el arreglo de las mejores puntuaciones
            startActivity(intent)  //Iniciamos el activity, como solo muestra las puntuaciones, no esperamos nada de vuelta
        }
        //Cuando se le da click al boton de configuraciones
        btnOpciones.setOnClickListener {
            val intent: Intent =
                Intent(this, ConfiguracionesActivity::class.java) //Se crea el intent
            intent.putExtra(
                EXTRA_CONFIGURACINES,
                model.configuraciones
            ) //Se mete en el intent las configuraciones del juego
            startActivityForResult(
                intent,      //Se inicia la activity de configuraciones
                CONFIGURACIONES_REQUEST_CODE    //La activity nos tiene que devolver un objeto de configuraciones con las configuraciones del juego
            )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            CONFIGURACIONES_REQUEST_CODE -> {   //Cuando nos devuelven las configuraciones
                when (resultCode) {
                    //Si todo salio bien
                    Activity.RESULT_OK -> model.configuraciones =   //A nuestro modelo que tiene las configuraciones se le asigna el resultado
                        data?.getSerializableExtra(EXTRA_RESULT_CONFIUGRACIONES) //El resultado es un objeto tipo serializable
                                as Configuraciones //Que casteamos a un objeto tipo Configuraciones
                    Activity.RESULT_CANCELED -> {}
                }
            }
            PREGUNTAS_REQUEST_CODE -> {
                when (resultCode) {
                    Activity.RESULT_OK -> model.mejoresPuntajes = data?.getSerializableExtra( //Sacamos la lista actualizada de mejores puntajes y se la metemos al modelo
                        EXTRA_RESULT_LISTA_ORDENADA_NUEVA
                    ) as ArrayList<Jugador>
                }

            }
        }
    }
    //endregion
}
