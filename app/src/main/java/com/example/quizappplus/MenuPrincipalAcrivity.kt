package com.example.quizappplus

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

class MenuPrincipalAcrivity : AppCompatActivity() {

    //region controlesVista
    private lateinit var btnJuego:Button
    private lateinit var btnOpciones:Button
    private lateinit var btnPuntuacion:Button
    //endregion

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

        Inicializar()

        btnJuego.setOnClickListener{
            val intent:Intent= Intent(this, PreguntasActivity::class .java)
            intent.putExtra("EXTRA_CONFIGURACIONES_VIEWMODEL_FORQUESTIONS",model.configuraciones)
            intent.putExtra("EXTRA_PUNTUACIONES_LIST_FORQUESTIONS",model.mejoresPuntajes)
            startActivityForResult(intent, PREGUNTAS_REQUEST_CODE)
        }

        btnPuntuacion.setOnClickListener{
            val intent:Intent=Intent(this,PuntuacionActivity::class.java)
            intent.putExtra(EXTRA_MEJORES_PUNTUACIONES,model.mejoresPuntajes)
            startActivity(intent)
        }

        btnOpciones.setOnClickListener{
            val intent:Intent= Intent(this,ConfiguracionesActivity::class.java)
            intent.putExtra("EXTRA_CONFIGURACINES_VIEWMODEL",model.configuraciones)
            startActivityForResult(intent,CONFIGURACIONES_REQUEST_CODE)
        }

    }

    private fun Inicializar()
    {
        if (model.FlagInicio == false){
            model.SetFlagInicioJuego()
            model.mejoresPuntajes.add(Jugador("Jose",1000,false,6))
            model.mejoresPuntajes.add(Jugador("Pedro",950,true,5))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            CONFIGURACIONES_REQUEST_CODE-> {
                when(resultCode){
                    Activity.RESULT_OK->model.configuraciones = data?.getSerializableExtra("EXTRA_RESULT_CONFIUGRACION_VIEWMODEL") as ConfiguracionesVM
                }
            }
            PREGUNTAS_REQUEST_CODE-> {
                when(resultCode){
                    Activity.RESULT_OK->model.mejoresPuntajes = data?.getSerializableExtra(
                        EXTRA_LISTA_ORDENADA_NUEVA) as ArrayList<Jugador>
                }

            }
        }
    }
    //endregion
}
