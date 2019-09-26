package com.example.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders

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

    //endregion
    private lateinit var jugadoresTvs:List<TextView>
    private lateinit var puntuacionesTvs:List<TextView>

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
        //endregion

        val listaPuntuaciones = intent.getSerializableExtra(EXTRA_MEJORES_PUNTUACIONES) as ArrayList<Jugador>
        model.SetJugadores(listaPuntuaciones)
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
        }
    }

    private fun InvisibilizarTvJugadores(){
        for (i in 0 until jugadoresTvs.size){
            jugadoresTvs[i].isVisible=false
            puntuacionesTvs[i].isVisible=false
        }
    }
}
