package com.example.quizappplus.DB

import android.content.Context
import androidx.room.Database
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizappplus.DB.DAOs.*
import com.example.quizappplus.DB.Entidades.*

@Database(
    entities =[
        AplicacionEntity::class,
        CategoriaEntity::class,
        ConfiguracionEntity::class,
        JuegoEntity::class,
        OpcionEntity::class,
        PreguntaEntity::class,
        PreguntaJuegoEntity::class,
        PuntuacionEntity::class,
        UsuarioEntity::class
    ],
    version = 1
)

abstract class AppDatabase:RoomDatabase(){

    abstract fun getAplicacionDao(): AplicacionDao

    abstract fun getCategoriaDao(): CategoriaDao

    abstract fun getConfiguracionDao(): ConfiguracionDao

    abstract fun getJuegoDao(): JuegoDao

    abstract fun getOpcionDao(): OpcionDao

    abstract fun getPreguntaDao(): PreguntaDao

    abstract fun getPreguntaJuegoDao(): PreguntaJuegoDao

    abstract fun getPuntuacionDao(): PuntuacionDao

    abstract fun getUsuarioDao(): UsuarioDao


    //Objeto singleton
    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase::class.java,
                    "QuizzAppPro.db"
                )
                    .allowMainThreadQueries()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            initializeData(db)
                        }
                    })
                    .build()
            }

            return INSTANCE as AppDatabase
        }

        fun initializeData(db: SupportSQLiteDatabase) {
            //db.beginTransaction();

            //region Categorias
            //Insertar Datos en la Tabla Cateforia
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (0, 'Cine')")
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (1, 'Música')")
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (2, 'Smash')")
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (3, 'Deporte')")
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (4, 'Historia')")
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (5, 'Varios')")
            //endregion

            //region Preguntas
            //Insertar Datos en la Tabla Pregunta
            //Preguntas Cine
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (0, 0, 'Nombre de la pelicula mas taquillera del 2019')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (1, 0, '¿Como se llama la serie de peliculas donde los juguetes cobran vida?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (2, 0, '¿Quién es el director de Piratas del caribe: La maldición del perla negra?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (3, 0, '¿Como se llamaba el burro que estaba siempre a lado de shrek?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (4, 0, '¿De que compañia es Las cronicas de Narnia: el león, la bruja y el ropero?')")

            //Preguntas Musica
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (5, 1, '¿Quién inventó el organo hidráulico?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (6, 1, '¿Quién fue el primer artista en grabar rock and roll?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (7, 1, '¿Cual de las siguientes obras no es de Mozart?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (8, 1, '¿Quién era el vocalista de Nirvana?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (9, 1, '¿Cual de las siguientes canciones no es de Queen?')")

            //Preguntas Smash
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (10, 2, '¿Quiénes fueron los creadores de Super Smash Bros?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (11, 2, '¿Cuántos personajes tiene el Super Smash Bros 64?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (12, 2, '¿Cuánto daño hace el Falcon Punch en el Super Smash Bros de la Wii U?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (13, 2, '¿Cuál es el movimiento especial de Jigglypuff hacia abajo?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (14, 2, '¿Quién es el jefe final de Super Smash Bros Brawl?')")

            //Preguntas Deporte
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (15, 3, '¿En qué año fueron los primeros Juegos Olímpicos modernos?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (16, 3, '¿Dónde se celebraron los primeros Juegos olimpicos de la era moderna?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (17, 3, 'Deporte que hace uso de un guante de cuero, un palo de madera o metal y una pelota de cuero')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (18, 3, '¿Cual es el deporta que practica Messi de manera profesional?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (19, 3, 'El salto de la garrocha consiste en...')")

            //Preguntas Historia
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (20, 4, '¿Quién fue el primer presidente de Estados Unidos?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (21, 4, '¿Qué periodo comprende la Segunda Guerra Mundial?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (22, 4, '¿Qué emperador romano legalizó el cristianismo y puso fin a la persecución de los cristianos?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (23, 4, '¿Qué hito informático de 1969 cambiaría radicalmente el curso de la historia de la humanidad?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (24, 4, '¿Por qué es significativo el Poema de Gilgamesh?')")

            //Preguntas Varios
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (25, 5, '2 + 2 = ')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (26, 5, 'El dicho \"Mi casa es tu casa\" se usa para...')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (27, 5, '¿Cuál fue la primera consola de videojuegos?')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (28, 5, 'El termino \"Cubreme las espaldas\" se usa para...')")
            db.execSQL("INSERT INTO Pregunta (idPregunts, idCategoria, texto) VALUES (29, 5, 'En el juego UNO cuando se tiene una carta se debe decir...')")
        //endregion

            //db.setTransactionSuccessful();
            //db.endTransaction();
        }
    }
}