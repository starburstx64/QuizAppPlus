package com.example.quizappplus.Vistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.quizappplus.DB.AppDatabase
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
    private lateinit var tvJugador7: TextView
    private lateinit var tvJugador8: TextView
    private lateinit var tvJugador9: TextView
    private lateinit var tvJugador10: TextView

    private lateinit var avatar1:ImageView
    private lateinit var avatar2:ImageView
    private lateinit var avatar3:ImageView
    private lateinit var avatar4:ImageView
    private lateinit var avatar5:ImageView
    private lateinit var avatar6:ImageView
    private lateinit var avatar7:ImageView
    private lateinit var avatar8:ImageView
    private lateinit var avatar9:ImageView
    private lateinit var avatar10:ImageView

    private lateinit var tvPuntuacion1: TextView
    private lateinit var tvPuntuacion2: TextView
    private lateinit var tvPuntuacion3: TextView
    private lateinit var tvPuntuacion4: TextView
    private lateinit var tvPuntuacion5: TextView
    private lateinit var tvPuntuacion6: TextView
    private lateinit var tvPuntuacion7: TextView
    private lateinit var tvPuntuacion8: TextView
    private lateinit var tvPuntuacion9: TextView
    private lateinit var tvPuntuacion10: TextView

    private lateinit var imagen1: ImageView
    private lateinit var imagen2: ImageView
    private lateinit var imagen3: ImageView
    private lateinit var imagen4: ImageView
    private lateinit var imagen5: ImageView
    private lateinit var imagen6: ImageView
    private lateinit var imagen7: ImageView
    private lateinit var imagen8: ImageView
    private lateinit var imagen9: ImageView
    private lateinit var imagen10: ImageView

    //endregion
    private lateinit var jugadoresTvs:List<TextView>
    private lateinit var puntuacionesTvs:List<TextView>
    private lateinit var AvataresTvs:List<ImageView>
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
        tvJugador7 = findViewById(R.id.player7)
        tvJugador8 = findViewById(R.id.player8)
        tvJugador9 = findViewById(R.id.player9)
        tvJugador10 = findViewById(R.id.player10)

        avatar1 = findViewById(R.id.puntaciones_p1_imageView)
        avatar2 = findViewById(R.id.puntaciones_p2_imageView)
        avatar3 = findViewById(R.id.puntaciones_p3_imageView)
        avatar4 = findViewById(R.id.puntaciones_p4_imageView)
        avatar5 = findViewById(R.id.puntaciones_p5_imageView)
        avatar6 = findViewById(R.id.puntaciones_p6_imageView)
        avatar7 = findViewById(R.id.puntaciones_p7_imageView)
        avatar8 = findViewById(R.id.puntaciones_p8_imageView)
        avatar9 = findViewById(R.id.puntaciones_p9_imageView)
        avatar1 = findViewById(R.id.puntaciones_p10_imageView)

        tvPuntuacion1 = findViewById(R.id.score1)
        tvPuntuacion2 = findViewById(R.id.score2)
        tvPuntuacion3 = findViewById(R.id.score3)
        tvPuntuacion4 = findViewById(R.id.score4)
        tvPuntuacion5 = findViewById(R.id.score5)
        tvPuntuacion6 = findViewById(R.id.score6)
        tvPuntuacion7 = findViewById(R.id.score7)
        tvPuntuacion8 = findViewById(R.id.score8)
        tvPuntuacion9 = findViewById(R.id.score9)
        tvPuntuacion10 = findViewById(R.id.score10)

        imagen1=findViewById(R.id.aimagen1)
        imagen2=findViewById(R.id.aimagen2)
        imagen3=findViewById(R.id.aimagen3)
        imagen4=findViewById(R.id.aimagen4)
        imagen5=findViewById(R.id.aimagen5)
        imagen6=findViewById(R.id.aimagen6)
        imagen7=findViewById(R.id.aimagen7)
        imagen8=findViewById(R.id.aimagen8)
        imagen9=findViewById(R.id.aimagen9)
        imagen10=findViewById(R.id.aimagen10)

        jugadoresTvs = listOf(
            tvJugador1,
            tvJugador2,
            tvJugador3,
            tvJugador4,
            tvJugador5,
            tvJugador6,
            tvJugador7,
            tvJugador8,
            tvJugador9,
            tvJugador10
        )
        AvataresTvs = listOf()
        puntuacionesTvs = listOf(
            tvPuntuacion1,
            tvPuntuacion2,
            tvPuntuacion3,
            tvPuntuacion4,
            tvPuntuacion5,
            tvPuntuacion6,
            tvPuntuacion7,
            tvPuntuacion8,
            tvPuntuacion9,
            tvPuntuacion10
        )

        imagenes = listOf(
            imagen1,
            imagen2,
            imagen3,
            imagen4,
            imagen5,
            imagen6,
            imagen7,
            imagen8,
            imagen9,
            imagen10
        )
        //endregion

        var dataBase = AppDatabase.getAppDatabase(this)

        model.SetJugadores(dataBase)
        InicializarTodo()
    }

    private fun InicializarTodo(){
        InvisibilizarTvJugadores()
        for (i in 0 until model.ListaPuntuaciones.size)
        {
            AvataresTvs[i].setImageResource(model.ListaPuntuaciones[i].imagenAvatar)
            AvataresTvs[i].isVisible=true
            jugadoresTvs[i].text = model.ListaPuntuaciones[i].nombreUsuario
            jugadoresTvs[i].isVisible=true
            puntuacionesTvs[i].text = model.ListaPuntuaciones[i].puntuacion.toString()
            puntuacionesTvs[i].isVisible=true
            if (model.ListaPuntuaciones[i].cheated)
            {
                imagenes[i].setImageResource(R.mipmap.bufon)
            }
        }
    }

    private fun InvisibilizarTvJugadores(){
        for (i in 0 until jugadoresTvs.size){
            jugadoresTvs[i].isVisible=false
            puntuacionesTvs[i].isVisible=false
            AvataresTvs[i].isVisible=false
        }
    }
}
