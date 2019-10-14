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

            //region Opciones
            //Insertar Datos en Tabla Opciones

            //Categora Cine
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (0, 'Avengers: Endgame', true, 0)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (1, 'Avengers: Infinity war', false, 0)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (2, 'Avatar', false, 0)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (3, 'Star Wars: Episodio VII - El despertar de la Fuerza', false, 0)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (4, 'Toy story', true, 1)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (5, 'Pequeños gigantes', false, 1)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (6, 'Disney Pixar', false, 1)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (7, 'Minions', false, 1)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (8, 'Gore Verbinski', true, 2)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (9, 'Hans Zimmer', false, 2)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (10, 'Ted Elliott', false, 2)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (11, 'Terry Rosio', false, 2)")

            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (12, 'Ninguna de las anteriores', true, 3)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (13, 'Burrito', false, 3)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (14, 'Asno', false, 3)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (15, 'Noble corsel', false, 3)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (16, 'Walt Daisney Pictures', true, 4)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (17, 'Warner Brothers', false, 4)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (18, 'Dreamworks', false, 4)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (19, 'Sony Pictures', false, 4)")


            //Categoria Musica
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (20, 'Ctesibio', true, 5)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (21, 'Sofocles', false, 5)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (22, 'Euripides', false, 5)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (23, 'Socrates', false, 5)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (24, 'Chuck Berry', true, 6)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (25, 'Elvis Presley', false, 6)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (26, 'Bill Haley', false, 6)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (27, 'Little Richard', false, 6)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (28, 'Madama Butterfly', true, 7)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (29, 'La flauta mágica', false, 7)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (30, 'Las bodas de Fígaro', false, 7)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (31, 'La falsa jardinera', false, 7)")

            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (32, 'Kurt Cobain', true, 8)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (33, 'Jim Morrison', false, 8)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (34, 'John Lennon', false, 8)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (35, 'Freddie Mercury', false, 8)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (36, '¿Where are we now?', true, 9)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (37, 'Dont Stop me now', false, 9)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (38, 'Tie your mother down', false, 9)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (39, 'These are the days of our lives', false, 9)")


            //Categotia Smash
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (40, 'Masahiro Sakurai y Satoru Iwata', true, 10)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (41, 'Satoru Iwata y Shigeru Miyamoto', false, 10)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (42, 'Reggie y Shigeru Miyamoto', false, 10)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (43, 'Masahiro Sakurai y Satoru Shibata', false, 10)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (44, '12', true, 11)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (45, '14', false, 11)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (46, '8', false, 11)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (47, '9', false, 11)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (48, '25%', true, 12)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (49, '35%', false, 12)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (50, '50%', false, 12)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (51, '30%', false, 12)")


            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (52, 'Se duerme y pega un golpe fulminante', true, 13)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (53, 'Hace un salto bomba', false, 13)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (54, 'Usa desenrollar', false, 13)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (55, 'Se va volando', false, 13)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (56, 'Tabuu', true, 14)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (57, 'Rayquaza', false, 14)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (58, 'Master Hand', false, 14)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (59, 'Ridley', false, 14)")


            //Categoria Deporte
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (60, '1896', true, 15)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (61, '1930', false, 15)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (62, '1658', false, 15)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (63, '2000', false, 15)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (64, 'Atenas', true, 16)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (65, 'Roma', false, 16)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (66, 'Londres', false, 16)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (67, 'Grecia', false, 16)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (68, 'Beisbol', true, 17)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (69, 'Basquetbol', false, 17)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (70, 'Futbol', false, 17)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (71, 'Cricket', false, 17)")

            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (72, 'Futbol Soccer', true, 18)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (73, 'Futbol americano', false, 18)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (74, 'Hockey', false, 18)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (75, 'Golf', false, 18)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (76, 'Saltar para superar una barra que esta situada a gran altura', true, 19)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (77, 'Ver quien salta mas alto', false, 19)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (78, 'Lanzar una vara lo mas lejos posible', false, 19)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (79, 'Equilibrase en la lanza', false, 19)")


            //Categoria Historia
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (80, 'George Washington', true, 20)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (81, 'Thomas Jefferson', false, 20)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (82, 'Abraham Lincoln\n', false, 20)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (83, 'John F. Kennedy', false, 20)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (84, '1939 - 1945', true, 21)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (85, '1936 - 1939', false, 21)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (86, '1914 - 1918', false, 21)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (87, '1950 - 1975', false, 21)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (88, 'Constantino ', true, 22)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (89, 'Trajano ', false, 22)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (90, 'Adriano', false, 22)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (91, 'Nerón ', false, 22)")

            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (92, 'Internet', true, 23)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (93, 'El primer ordenador personal', false, 23)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (94, 'El primer router wi-fi', false, 23)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (95, 'El primer iPod', false, 23)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (96, 'Es la primera obra épica que hace referencia a la inmortalidad y la percepción humana del alma', true, 24)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (97, 'Es el primer documento escrito sobre la adopción del monoteísmo en el Medio Oriente', false, 24)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (98, 'Fue un libro de estrategia militar de 500 páginas que sirvió en la antigua Mesopotamia', false, 24)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (99, 'El tratado más antiguo que existe sobre el Inframundo', false, 24)")


            //Categoria Varios
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (100, '4', true, 25)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (101, '2', false, 25)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (102, '-4', false, 25)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (103, '0', false, 25)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (104, 'Invitar personas a la casa\n', true, 26)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (105, 'Ahuyentar a la gente de la casa', false, 26)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (106, 'Invitar a vivir a alguien a la casa', false, 26)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (107, 'Saludar a alguien', false, 26)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (108, 'Magnavox Odyssey', true, 27)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (109, 'Super Nintendo', false, 27)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (110, 'Xbox', false, 27)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (111, 'Atari 2600', false, 27)")

            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (112, 'Defender su retarguardia', true, 28)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (113, 'Tapar su espalda', false, 28)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (114, 'Dejarlo atras', false, 28)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (115, 'Hacer nada', false, 28)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (116, 'UNO!', true, 29)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (117, 'GANE', false, 29)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (118, 'SAFE', false, 29)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (119, 'LOTERIA', false, 29)")
            //endregion

            //region Configuracion
            db.execSQL("INSERT INTO Configuracion (idConfiguracion, categoriasUsadas, numeroPreguntas, dificultad, pistasEnabled, numeroPistas) VALUES (0, 1, 5, 0, false, 3)")
            //endregion

            //region Usuario
            db.execSQL("INSERT INTO Usuario (idUsuario, idConfiguracion, idAplicacion, imagenAvatar, userName, contraseña) VALUES (0, 0, 0, null, 'alfa', 'notiene')")
            //endregion

            //region Juego
            db.execSQL("INSERT INTO Juego (idJuego, idUsuario, estatusJuego, cheated) VALUES (0, 0, 'Terminado', false)")
            //endregion

            //db.setTransactionSuccessful();
            //db.endTransaction();
        }
    }
}