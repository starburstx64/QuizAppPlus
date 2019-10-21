package com.example.quizappplus.VistaModelos

import androidx.lifecycle.ViewModel
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.Modelos.*
import kotlin.random.Random

class PreguntasVM : ViewModel() {
    lateinit var configuraciones:Configuraciones    //Son las configuraciones que tiene el juego
    lateinit var listaPuntuaciones:MutableList<Usuario> //Son las puntuaciones maximas que hay, vienen aqui para pasarse a la activity final
    private var flagJuegoIniciado = false   //Sirve para hacer las configuraciones la primera vez que se abre la activity
    private var flagUsoPista = false    //Marca si en todo el juego se uso una pista, sirve para saber si mostrar un joker
    var juegoTerminado = false  //Marca si ya se termino el juego, si se vuelve a la activity y el juego ya termino, te permite navegar
                                //por las preguntas sin que se abra la pantalla de puntuacion final
    private lateinit var questions: List<Pregunta>  //las preguntas que se usaran en el juego, seleccinadas aleatoriamente segun las categorias seleccionadas
    private lateinit var flagQuestions: MutableList<Boolean>    //Esto sirve para saber si ya se paso por la pregunta mas de una vez
                                                                //Si es la primera vez, la bandera esta en false y no se muestra el TV de estado
    private var currentQuestion: Int = 0    //Para saber la pregunta actual
    private lateinit var nombreJugador: String  //Para guardar el nombre del jugador, se obtiene cuando el juego termina, devuelto por otra activity
    private var preguntasContestadas = 0    //Guarda el contador de las preguntas contestadas
    private var pistasUsadas: Int = 0       //Guarda el contador de cuantas pístas se han usado
    private var JuegoIniciado: Boolean = false  //Guarda el dato para que saber si el juego esta inicializado o no
    //propiedades
    val FlagJuegoIniciado get() = flagJuegoIniciado
    val PreguntasContestadas get() = preguntasContestadas
    val NombreJugador get() = nombreJugador
    val numOfQuestions get() = questions.size
    val numQuestion get() = currentQuestion
    val flagQuestion get() = flagQuestions[currentQuestion]
    val FlagUsoPista get() = flagUsoPista

    fun UsoPista() {
        flagUsoPista = true
    }

    fun ActualizaFlag() {
        flagQuestions[currentQuestion] = true
    }

    fun SetNombre(nombre: String) {
        nombreJugador = nombre
    }

    fun getPistasUsadas() = pistasUsadas

    //Actualiza los campos referentes a las pistas
    fun usarPista() {
        pistasUsadas++  //Aumenta el contador de pistas usadas
        UsoPista()      //Marca que uso una pista en el juego , por lo que se mostrara un joker al final
        questions[currentQuestion].usoPista = true  //Marca que en la pregunta actual se uso una pista
    }

    fun InicializarJuego(db: AppDatabase,juegoIniciado:Boolean){
        if (!FlagJuegoIniciado) {
            //Marcamos que el juego ya comenzo
            flagJuegoIniciado=true
            JuegoIniciado=juegoIniciado
            //Guardamos las configuraciones
            this.configuraciones=configuraciones
            this.listaPuntuaciones=listaPuntuaciones //Guardamos la lista de puntuaciones
            //Sacamos la lista con las categorias que usaremos
    //      val CategoriasUsadas = configuraciones.usedCategoriesIds
            //y la usamos para escoger las preguntas al azar
            if(JuegoIniciado==false) {
                SetQuestions(db, configuraciones.numPregunta)
            }
            else {
                questions = Pregunta.GetPreguntasUsadas(db)
                setFlags()
            }

            //Ponemos las cosas a la dificultad adecuada
            SetQuestionsOptions(configuraciones.dificultad)
        }
    }

    fun SetQuestions(db: AppDatabase, numPreguntas: Int) {
        //Sacar todas las preguntas de las categorias seleccionadas
        var preguntasPotenciales: MutableList<Pregunta> = mutableListOf()
        preguntasPotenciales.addAll(0,Pregunta.GetPreguntasDisponibles(db,configuraciones.usedCategoriesIds))
        //Escoge aleatoriamente las preguntas
        var preguntasSeleccionadas: MutableList<Pregunta> = mutableListOf()
        for (i in 0 until numPreguntas) {
            var selectedIndex = Random.nextInt(0, preguntasPotenciales.size)    //Escoge un numero al asar segun todas las preguntas disponibles
            preguntasSeleccionadas.add(preguntasPotenciales[selectedIndex]) //agrega la pregunta correspondiente a ese numero a las preguntas que se usaran
            preguntasPotenciales.removeAt(selectedIndex)    //Elimina la pregunta de las preguntas disponibles para que no se pueda repetir la pregunta
        }

        Pregunta.SetPreguntasUsadas(db,preguntasSeleccionadas)
        questions=Pregunta.GetPreguntasUsadas(db)

        setFlags()  //Marca que ninguna pregunta se ha visitado mas de una vez todavia, por lo que no se mostrara el mensaje en la parte de abajo
    }

    //Se llama cuando se escogen las preguntas para marcar que ninguna ha sido visitada mas de una vez todavia
    private fun setFlags() {
        var listaFlags: MutableList<Boolean> = mutableListOf()
        for (i in 0 until questions.size) {
            listaFlags.add(false)
        }
        flagQuestions = listaFlags
    }

    //Para cada pregunta marca que opciones se utilizaran
    fun SetQuestionsOptions(dificultad: Int) {
        //Para cada una de las preguntas
        for (i in 0 until questions.size) {
            //Segun la dificultad, cada pregunta tiene 3 opciones que son falsas y una verdadera.
            //Vamos a escoger aleatoriamente cuales de las falsas no apareceran
            var numDisponibles: MutableList<Int> = mutableListOf(1, 2, 3)
            when (dificultad) {
                1 -> {  //Para dificultad facil, de las 3 opciones falsas se eliminaran 2
                    numDisponibles.removeAt(Random.nextInt(0, numDisponibles.size))
                    numDisponibles.removeAt(Random.nextInt(0, numDisponibles.size))
                }
                2 -> {  //Para dificultad media se eliminara 1, para dificil no se eliminara ninguna
                    numDisponibles.removeAt(Random.nextInt(0, numDisponibles.size))
                }
            }
            numDisponibles.add(0)   //se añade el valor 0, por que la opcion verdadera siempre es la posición 0
            var ordenOpciones: MutableList<Int> = mutableListOf()   //Con esta lista sabremos en que orden estaran las opciones
            for (j in 0..dificultad) { //En facil se hara 2 veces, en medio 3 y en dificil 4
                var selectedIndex = Random.nextInt(0, numDisponibles.size)  //se selecciona un indice de los numeros disponibles
                ordenOpciones.add(numDisponibles[selectedIndex])    //Ese numero escogido al azar sera puesto en el orden de las opciones
                            //Por ejemplo, si los disponibles eran el 0 y el 3, si se selecciona el 3 primero, significa que al boton 1
                            //Se le asignara la opcion 3 de las opciones que tiene la pregunta
                numDisponibles.removeAt(selectedIndex)  //se quita la opcion de las opciones disponibles para que no se pueda repetir
            }
            questions[i].ordenOpciones = ordenOpciones  //A cada pregunta se le pone el orden de sus opciones para los botones
        }
    }

    fun getCurrentQuestion() = questions[currentQuestion]   //Devuelve el objeto pregunta seleccionado actualmente

    fun getQuestion(index: Int) = questions[index]  //Devuelve un objeto pregunta que pidas, nunca utilice esto pero por si acaso

    fun nextQuestion() {    //Cambia el objeto actual al siguiente de la lista
        currentQuestion = (currentQuestion + 1) % questions.size
    }

    fun previousQuestion() {    //Cambia el objeto actual al anterior de la lista
        currentQuestion = (currentQuestion + questions.size - 1) % questions.size
    }

    fun GetNumRespuestasCorrectas(): Int {  //Saca cuantas respuestas correctas tuvimos en el juego, se hace al final de la partida
        var contRespuestasCorrectas = 0
        for (i in 0 until questions.size) {
            contRespuestasCorrectas =
                if (questions[i].correcta)
                    contRespuestasCorrectas + 1
                else
                    contRespuestasCorrectas
        }
        return contRespuestasCorrectas
    }

    fun GetAcuracy(): Double {  //Calcula la presición del jugador para ver que imagen se mostrara
        return (GetNumRespuestasCorrectas().toDouble() / numOfQuestions.toDouble()) * 100.0
    }

    fun GetPuntajeFinal(): Int {    //Calcula el puntaje final
        var puntaje: Int = 0
        for (i in 0 until questions.size) {
            if (questions[i].correcta) {
                if (questions[i].usoPista) {    //si respondio correctamente con pista, solo obtendra 50 puntos
                    puntaje += 50
                } else  //Si la respondio correctamente sin pista, obtendra 100
                    puntaje += 100
            }
        }
        return puntaje
    }

    //Hace los ajustes correspondientes a la pregunta actual al ser contestada
    fun ContestarPregunta(index:Int){
        val pregunta= getCurrentQuestion()  //Obtiene la pregunta actual
        pregunta.contestada=true    //la marca como contestada
        pregunta.correcta=GetOpcionPreguntaActual(index).answer //obtiene si la opcion seleccionada
                                                                // era correcta o incorrecta y se lo asigna a la pregunta
        preguntasContestadas++  //Aumenta el contador de preguntas contestadas
    }

    fun GetOpcionPreguntaActual(index:Int):Opcion
    {
        //Sacamos la pregunta del modelo
        val pregunta = getCurrentQuestion()
        //Sacamos las opciones de la pregunta
        val opciones = pregunta.opciones
        //Sacamos la lista que dice en que orden van las opciones
        val ordenOpciones = pregunta.ordenOpciones
        //retornamos la opcion correspondiente al boton que se apreto.
        //orden opciones[index] saca el indice de la opcion de la pregunta correspondia al boton apretado
        //Ese indice que se obtuvo es la posición en el arreglo de opciones de la opcion correspondiente al boton que se apreto
        return opciones[ordenOpciones[index]]
    }

}