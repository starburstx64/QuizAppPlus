package com.example.quizappplus

import androidx.lifecycle.ViewModel
import java.io.Serializable

class ConfiguracionesVM:ViewModel(),Serializable {
    //region PreguntasCine
    var preguntasCine:List<Pregunta> = listOf(
        Pregunta(R.string.cine1, listOf(
            Opcion("Avengers:Endgame",true),
            Opcion("Avengers:Infinity war",false),
            Opcion("Avatar",false),
            Opcion("Star Wars: Episodio VII - El despertar de la Fuerza",false)
        )),
        Pregunta(R.string.cine2, listOf(
            Opcion("Toy Story",true),
            Opcion("Pequeños gigantes",false),
            Opcion("Disney Pixar",false),
            Opcion("Minions",false)
        )),
        Pregunta(R.string.cine3, listOf(
            Opcion("Gore Verbinski",true),
            Opcion("Hans Zimmer",false),
            Opcion("Ted Elliott",false),
            Opcion("Terry Rosio",false)
        )),
        Pregunta(R.string.cine4, listOf(
            Opcion("Ninguna de las anteriores",true),
            Opcion("Burrito",false),
            Opcion("Asno",false),
            Opcion("Noble corsel",false)
        )),
        Pregunta(R.string.cine5, listOf(
            Opcion("Walt Disney Pictures",true),
            Opcion("Warner Brothers",false),
            Opcion("Dreamworks",false),
            Opcion("Sony Pictures",false)
        ))
    )
    //endregion
    //region PreguntasMusica
    var preguntasMusica:List<Pregunta> = listOf(
        Pregunta(R.string.musica1, listOf(
            Opcion("Ctesibio",true),
            Opcion("Sofocles",true),
            Opcion("Euripides",true),
            Opcion("Socrates",true)

        )),
        Pregunta(R.string.musica2, listOf(
            Opcion("Chuck Berry",true),
            Opcion("Elvis Presley",true),
            Opcion("Bill Haley",true),
            Opcion("Little Richard",true)
        )),
        Pregunta(R.string.musica3, listOf(
            Opcion("Madama Butterfly",true),
            Opcion("La flauta mágica",true),
            Opcion("Las bodas de Fígaro",true),
            Opcion("La falsa jardinera",true)
        )),
        Pregunta(R.string.musica4, listOf(
            Opcion("Kurt Cobain",true),
            Opcion("Jim Morrison",true),
            Opcion("John Lennon",true),
            Opcion("Freddie Mercury",true)
        )),
        Pregunta(R.string.musica5, listOf(
            Opcion("Where are we now",true),
            Opcion("Don´t Stop me now",true),
            Opcion("Tie your mother down",true),
            Opcion("There are the days of our lives",true)
        ))
    )
    //endregion

    var categorias:List<Categoria>
    var numPregunta:Int = 5
    var dificultad:Int = 2
    var pistas: Boolean = false
    var numPistas:Int = 1


    init {
        categorias = listOf(
            Categoria("Cine",true,preguntasCine),
            Categoria("Música",false,preguntasMusica)
        )
    }

}