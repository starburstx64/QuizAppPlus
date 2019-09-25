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

        var puntuacionesMaximas:ArrayList<Jugador> = arrayListOf(Jugador("Jose",1000,false,6),
            Jugador("Pedro",950,true,5))

        btnJuego.setOnClickListener{
            val intent:Intent= Intent(this, PreguntasActivity::class .java)
            intent.putExtra("EXTRA_CONFIGURACIONES_VIEWMODEL_FORQUESTIONS",model.configuraciones)
            intent.putExtra("EXTRA_PUNTUACIONES_LIST_FORQUESTIONS",puntuacionesMaximas)
            startActivity(intent)
        }

        btnPuntuacion.setOnClickListener{
            val intent:Intent=Intent(this,PuntuacionActivity::class.java)

            startActivity(intent)
        }

        btnOpciones.setOnClickListener{
            val intent:Intent= Intent(this,ConfiguracionesActivity::class.java)
            intent.putExtra("EXTRA_CONFIGURACINES_VIEWMODEL",model.configuraciones)
            startActivityForResult(intent,CONFIGURACIONES_REQUEST_CODE)
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
        }
    }
    //endregion
}
