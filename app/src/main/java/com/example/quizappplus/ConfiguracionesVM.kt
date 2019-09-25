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
            Opcion("Sofocles",false),
            Opcion("Euripides",false),
            Opcion("Socrates",false)

        )),
        Pregunta(R.string.musica2, listOf(
            Opcion("Chuck Berry",true),
            Opcion("Elvis Presley",false),
            Opcion("Bill Haley",false),
            Opcion("Little Richard",false)
        )),
        Pregunta(R.string.musica3, listOf(
            Opcion("Madama Butterfly",true),
            Opcion("La flauta mágica",false),
            Opcion("Las bodas de Fígaro",false),
            Opcion("La falsa jardinera",false)
        )),
        Pregunta(R.string.musica4, listOf(
            Opcion("Kurt Cobain",true),
            Opcion("Jim Morrison",false),
            Opcion("John Lennon",false),
            Opcion("Freddie Mercury",false)
        )),
        Pregunta(R.string.musica5, listOf(
            Opcion("Where are we now",true),
            Opcion("Don´t Stop me now",false),
            Opcion("Tie your mother down",false),
            Opcion("There are the days of our lives",false)
        ))
    )
    //endregion
    //region PreguntasSmash
    var preguntasSmash:List<Pregunta> = listOf(
        Pregunta(R.string.smash1, listOf(
            Opcion("Masahiro Sakurai y Satoru Iwata",true),
            Opcion("Satoru Iwata y Shigeru Miyamoto",false),
            Opcion("Reggie y Shigeru Miyamoto",false),
            Opcion("Masahiro Sakurai y Satoru Shibata",false)

        )),
        Pregunta(R.string.smash2, listOf(
            Opcion("12",true),
            Opcion("14",false),
            Opcion("8",false),
            Opcion("9",false)
        )),
        Pregunta(R.string.smash3, listOf(
            Opcion("25%",true),
            Opcion("35%",false),
            Opcion("50%",false),
            Opcion("30%",false)
        )),
        Pregunta(R.string.smash4, listOf(
            Opcion("Se duerme y pega un golpe fulminante",true),
            Opcion("Hace un salto bomba",false),
            Opcion("Usa desenrollar",false),
            Opcion("Se va volando",false)
        )),
        Pregunta(R.string.smash5, listOf(
            Opcion("Tabuu",true),
            Opcion("Rayquaza",false),
            Opcion("Master Hand",false),
            Opcion("Ridley",false)
        ))
    )
    //endregion
    //region PreguntasDeporte
    var preguntasDeporte:List<Pregunta> = listOf(
        Pregunta(R.string.deporte1, listOf(
            Opcion("1896",true),
            Opcion("1930",false),
            Opcion("1658",false),
            Opcion("2000",false)

        )),
        Pregunta(R.string.deporte2, listOf(
            Opcion("Atenas",true),
            Opcion("Roma",false),
            Opcion("Londres",false),
            Opcion("Grecia",false)
        )),
        Pregunta(R.string.deporte3, listOf(
            Opcion("Beisbol",true),
            Opcion("Basquetbol",false),
            Opcion("Futbol",false),
            Opcion("Cricket",false)
        )),
        Pregunta(R.string.deporte4, listOf(
            Opcion("Futbol Soccer",true),
            Opcion("Futbol americano",false),
            Opcion("Hockey",false),
            Opcion("Golf",false)
        )),
        Pregunta(R.string.deporte5, listOf(
            Opcion("Saltar para superar una barra que esta situada a gran altura",true),
            Opcion("Ver quien salta mas alto",false),
            Opcion("Lanzar una vara lo mas lejos posible",false),
            Opcion("Equilibrase en la lanza",false)
        ))
    )
    //endregion
    //region PreguntasHistoria
    var preguntasHistoria:List<Pregunta> = listOf(
        Pregunta(R.string.historia1, listOf(
            Opcion("George Washington",true),
            Opcion("Thomas Jefferson",false),
            Opcion("Abraham Lincoln",false),
            Opcion("John F. Kennedy",false)

        )),
        Pregunta(R.string.historia2, listOf(
            Opcion("1939 - 1945",true),
            Opcion("1936 - 1939",false),
            Opcion("1914 - 1918",false),
            Opcion("1950 - 1975",false)
        )),
        Pregunta(R.string.historia3, listOf(
            Opcion("Constantino",true),
            Opcion("Trajano",false),
            Opcion("Adriano",false),
            Opcion("Nerón",false)
        )),
        Pregunta(R.string.historia4, listOf(
            Opcion("Internet",true),
            Opcion("El primer ordenador personal",false),
            Opcion("El primer router wi-fi",false),
            Opcion("El primer iPod",false)
        )),
        Pregunta(R.string.historia5, listOf(
            Opcion("Es la primera obra épica que hace referencia a la inmortalidad y la percepción humana del alma",true),
            Opcion("Es el primer documento escrito sobre la adopción del monoteísmo en el Medio Oriente",false),
            Opcion("Fue un libro de estrategia militar de 500 páginas que sirvió en la antigua Mesopotamia",false),
            Opcion("El tratado más antiguo que existe sobre el Inframundo",false)
        ))
    )
    //endregion
    //region PreguntasVarios
    var preguntasVarios:List<Pregunta> = listOf(
        Pregunta(R.string.varios1, listOf(
            Opcion("4",true),
            Opcion("2",false),
            Opcion("-4",false),
            Opcion("0",false)

        )),
        Pregunta(R.string.varios2, listOf(
            Opcion("Invitar personas a la casa",true),
            Opcion("Ahuyentar a la gente de la casa",false),
            Opcion("Invitar a vivir a alguien a la casa",false),
            Opcion("Saludar a alguien ",false)
        )),
        Pregunta(R.string.varios3, listOf(
            Opcion("Magnavox Odyssey",true),
            Opcion("Super Nintendo",false),
            Opcion("Xbox",false),
            Opcion("Atari 2600",false)
        )),
        Pregunta(R.string.varios4, listOf(
            Opcion("Defender su retarguardia ",true),
            Opcion("Tapar su espalda",false),
            Opcion("Dejarlo atras",false),
            Opcion("Hacer nada",false)
        )),
        Pregunta(R.string.varios5, listOf(
            Opcion("UNO!",true),
            Opcion("GANE",false),
            Opcion("SAFE",false),
            Opcion("LOTERIA",false)
        ))
    )
    //endregion

    var categorias:List<Categoria>
    var numPregunta:Int = 5
    var dificultad:Int = 2
    var pistas: Boolean = false
    var numPistas:Int = 2


    init {
        categorias = listOf(
            Categoria("Cine",true,preguntasCine),
            Categoria("Música",false,preguntasMusica),
            Categoria("Smash", false,preguntasSmash),
            Categoria("Deporte", false,preguntasDeporte),
            Categoria("Historia", false,preguntasHistoria),
            Categoria("Varios", false,preguntasVarios)
        )
    }

    fun GetCategoriasUsadas():List<Categoria>
    {
        var catUsadas:MutableList<Categoria> = mutableListOf()
        for (i in 0..categorias.size-1)
        {
            if(categorias[i].seleccionada==true)
                catUsadas.add(categorias[i])
        }
        return catUsadas
    }

}