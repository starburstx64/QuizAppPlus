package com.example.quizappplus.Vistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.quizappplus.Modelos.Jugador
import com.example.quizappplus.R
import com.example.quizappplus.VistaModelos.PuntuacionesVM

const val EXTRA_MEJORES_PUNTUACIONES="com.example.quizappPlus.EXTRA_MEJORES_PUNTUACIONES"

class PuntuacionActivity : AppCompatActivity() {

    //region objetosVista

    private lateinit var menuPrincipalButton:Button
    private lateinit var tvJugador1: TextView
    private lateinit var tvJugador2: TextView
    private lateinit var tvJugador3: TextView
    private lateinit var tvJugador4: TextView
    private lateinit var tvJugador5: TextView
    private lateinit var tvJugador6: TextView

    private lateinit var tvPuntuacion1: TextView
    private lateinit var tvPuntuacion2: TextView
    private lateinit var tvPuntuacion3: TextView
    private lateinit var tvPuntuacion4: TextView
    private lateinit var tvPuntuacion5: TextView
    private lateinit var tvPuntuacion6: TextView

    private lateinit var imagen1: ImageView
    private lateinit var imagen2: ImageView
    private lateinit var imagen3: ImageView
    private lateinit var imagen4: ImageView
    private lateinit var imagen5: ImageView
    private lateinit var imagen6: ImageView

    //endregion
    private lateinit var jugadoresTvs:List<TextView>
    private lateinit var puntuacionesTvs:List<TextView>
    private lateinit var imagenes:List<ImageView>

    private val model by lazy { ViewModelProviders.of(this)[PuntuacionesVM::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntuacion)

        //region InicializarControles
        menuPrincipalButton=findViewById(R.id.menu_puntuaciones_button)

        tvJugador1 = findViewById(R.id.player1)
        tvJugador2 = findViewById(R.id.player2)
        tvJugador3 = findViewById(R.id.player3)
        tvJugador4 = findViewById(R.id.player4)
        tvJugador5 = findViewById(R.id.player5)
        tvJugador6 = findViewById(R.id.player6)

        tvPuntuacion1 = findViewById(R.id.score1)
        tvPuntuacion2 = findViewById(R.id.score2)
        tvPuntuacion3 = findViewById(R.id.score3)
        tvPuntuacion4 = findViewById(R.id.score4)
        tvPuntuacion5 = findViewById(R.id.score5)
        tvPuntuacion6 = findViewById(R.id.score6)

        imagen1=findViewById(R.id.aimagen1)
        imagen2=findViewById(R.id.aimagen2)
        imagen3=findViewById(R.id.aimagen3)
        imagen4=findViewById(R.id.aimagen4)
        imagen5=findViewById(R.id.aimagen5)
        imagen6=findViewById(R.id.aimagen6)

        jugadoresTvs = listOf(
            tvJugador1,
            tvJugador2,
            tvJugador3,
            tvJugador4,
            tvJugador5,
            tvJugador6
        )
        puntuacionesTvs = listOf(
            tvPuntuacion1,
            tvPuntuacion2,
            tvPuntuacion3,
            tvPuntuacion4,
            tvPuntuacion5,
            tvPuntuacion6
        )

        imagenes = listOf(
            imagen1,
            imagen2,
            imagen3,
            imagen4,
            imagen5,
            imagen6
        )
        //endregion

        model.Inicializar()
        InicializarTodo()
    }

    private fun InicializarTodo(){
        InvisibilizarTvJugadores()
        for (i in 0 until model.ListaJugadores.size)
        {
            jugadoresTvs[i].text = model.ListaJugadores[i].nombre
            jugadoresTvs[i].isVisible=true
            puntuacionesTvs[i].text = model.ListaJugadores[i].puntuacion.toString()
            puntuacionesTvs[i].isVisible=true
            if (model.ListaJugadores[i].usoCheats)
            {
                imagenes[i].setImageResource(R.mipmap.bufon)
            }
        }
    }

    private fun InvisibilizarTvJugadores(){
        for (i in 0 until jugadoresTvs.size){
            jugadoresTvs[i].isVisible=false
            puntuacionesTvs[i].isVisible=false
        }
    }
}
