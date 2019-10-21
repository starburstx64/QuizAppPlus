package com.example.quizappplus.Vistas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.Modelos.Usuario
import com.example.quizappplus.R
import com.example.quizappplus.VistaModelos.PuntuacionFinalVM
import kotlinx.android.synthetic.main.activity_puntuacion_final.*

const val PUNTUACION_FINAL_ACTIVITY_RESULT=1200
const val EXTRA_JUGADOR_ACTUAL="com.example.quizappPlus.EXTRA_JUGADOR_ACTUAL"
const val EXTRA_LISTA_PUNTUACIONES="com.example.quizappPlus.EXTRA_LISTA_PUNTUACIONES"

class PuntuacionFinalActivity : AppCompatActivity() {

    //region controlesVista
    //textViews
    private lateinit var puntajeFinalTextView:TextView

    private lateinit var tvJugador1: TextView
    private lateinit var tvJugador2: TextView
    private lateinit var tvJugador3: TextView
    private lateinit var tvJugador4: TextView
    private lateinit var tvJugador5: TextView
    private lateinit var tvJugador6: TextView

    private lateinit var avatar1: ImageView
    private lateinit var avatar2: ImageView
    private lateinit var avatar3: ImageView
    private lateinit var avatar4: ImageView
    private lateinit var avatar5: ImageView
    private lateinit var avatar6: ImageView

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
    //imageViews
    private lateinit var imagenFinalImageView:ImageView
    //Buttons
    private lateinit var menuPrincipalButton:Button
    //endregion
    private val model by lazy { ViewModelProviders.of(this)[PuntuacionFinalVM::class.java] }
    private lateinit var jugadoresTvs:List<TextView>
    private lateinit var AvataresTvs: List<ImageView>
    private lateinit var puntuacionesTvs:List<TextView>
    private lateinit var imagenes:List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntuacion_final)

        //region InicializarControles
        puntajeFinalTextView=findViewById(R.id.puntajeFinal)

        tvJugador1 = findViewById(R.id.jugador1)
        tvJugador2 = findViewById(R.id.jugador2)
        tvJugador3 = findViewById(R.id.jugador3)
        tvJugador4 = findViewById(R.id.jugador4)
        tvJugador5 = findViewById(R.id.jugador5)
        tvJugador6 = findViewById(R.id.jugador6)

        avatar1 = findViewById(R.id.imagenperfil_p1_puntuacionfinal)
        avatar2 = findViewById(R.id.imagenperfil_p2_puntuacionfinal)
        avatar3 = findViewById(R.id.imagenperfil_p3_puntuacionfinal)
        avatar4 = findViewById(R.id.imagenperfil_p4_puntuacionfinal)
        avatar5 = findViewById(R.id.imagenperfil_p5_puntuacionfinal)
        avatar6 = findViewById(R.id.imagenperfil_p6_puntuacionfinal)

        tvPuntuacion1 = findViewById(R.id.puntiacion1)
        tvPuntuacion2 = findViewById(R.id.puntiacion2)
        tvPuntuacion3 = findViewById(R.id.puntiacion3)
        tvPuntuacion4 = findViewById(R.id.puntiacion4)
        tvPuntuacion5 = findViewById(R.id.puntiacion5)
        tvPuntuacion6 = findViewById(R.id.puntiacion6)

        imagen1=findViewById(R.id.imagen1)
        imagen2=findViewById(R.id.imagen2)
        imagen3=findViewById(R.id.imagen3)
        imagen4=findViewById(R.id.imagen4)
        imagen5=findViewById(R.id.imagen5)
        imagen6=findViewById(R.id.imagen6)

        jugadoresTvs = listOf(
            tvJugador1,
            tvJugador2,
            tvJugador3,
            tvJugador4,
            tvJugador5,
            tvJugador6
        )

        AvataresTvs = listOf(
            avatar1,
            avatar2,
            avatar3,
            avatar4,
            avatar5,
            avatar6
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

        imagenFinalImageView=findViewById(R.id.imagenFinal)

        menuPrincipalButton=findViewById(R.id.menu_button)
        //endregion

        var dataBase = AppDatabase.getAppDatabase(this)

        model.PuntuacionUsuario = intent.getIntExtra("PuntuacionUsuario",0)
        model.Acuracy= intent.getDoubleExtra("PorcentajeUsuario",0.0)

        menuPrincipalButton.isVisible=false
        model.SetJugadores(dataBase)
        InicializarTodo()

    }

    private fun InicializarTodo(){
        InvisibilizarTvJugadores()
        for (i in 0 until model.ListaPuntuaciones.size)
        {
            AvataresTvs[i].setImageResource(model.ListaPuntuaciones[i].imagenAvatar)
            AvataresTvs[i].isVisible = true
            jugadoresTvs[i].text = model.ListaPuntuaciones[i].nombreUsuario
            jugadoresTvs[i].isVisible = true
            puntuacionesTvs[i].text = model.ListaPuntuaciones[i].puntuacion.toString()
            puntuacionesTvs[i].isVisible = true
            if (model.ListaPuntuaciones[i].cheated) {
                imagenes[i].setImageResource(R.mipmap.bufon)
            }
        }
        puntajeFinalTextView.text="${model.PuntuacionUsuario.toString()}"
        SetImagenFinal()
    }

    private fun SetImagenFinal()
    {
        var usoPista = intent.getBooleanExtra("Tramposo",false)
        if(usoPista)
        {
            imagenFinalImageView.setImageResource(R.mipmap.bufon)
        }
        else
        {
            imagenFinalImageView.setImageResource(R.mipmap.manita_arriba)
        }
    }

    private fun InvisibilizarTvJugadores(){
        for (i in 0 until jugadoresTvs.size){
            jugadoresTvs[i].isVisible = false
            puntuacionesTvs[i].isVisible = false
            AvataresTvs[i].isVisible = false
            imagenes[i].isVisible = false
        }
    }
}
