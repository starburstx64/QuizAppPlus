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
            db.beginTransaction()

            // Insertar la aplicacion
            db.execSQL("INSERT INTO Aplicacion (idAplicacion, idUsuarioActivo) VALUES (0, null)")

            //Insertar Datos en la Tabla Cateforia
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (0, 'Cine')")
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (1, 'Música')")
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (2, 'Smash')")
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (3, 'Deporte')")
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (4, 'Historia')")
            db.execSQL("INSERT INTO Categoria (idCategoria, nombre) VALUES (5, 'Varios')")


            //region Preguntas
            //Insertar Datos en la Tabla Pregunta
            //Preguntas Cine
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (0, 0, 'Nombre de la pelicula mas taquillera del 2019')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (1, 0, '¿Como se llama la serie de peliculas donde los juguetes cobran vida?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (2, 0, '¿Quién es el director de Piratas del caribe: La maldición del perla negra?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (3, 0, '¿Como se llamaba el burro que estaba siempre a lado de shrek?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (4, 0, '¿De que compañia es Las cronicas de Narnia: el león, la bruja y el ropero?')")

            //Preguntas Musica
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (5, 1, '¿Quién inventó el organo hidráulico?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (6, 1, '¿Quién fue el primer artista en grabar rock and roll?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (7, 1, '¿Cual de las siguientes obras no es de Mozart?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (8, 1, '¿Quién era el vocalista de Nirvana?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (9, 1, '¿Cual de las siguientes canciones no es de Queen?')")

            //Preguntas Smash
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (10, 2, '¿Quiénes fueron los creadores de Super Smash Bros?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (11, 2, '¿Cuántos personajes tiene el Super Smash Bros 64?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (12, 2, '¿Cuánto daño hace el Falcon Punch en el Super Smash Bros de la Wii U?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (13, 2, '¿Cuál es el movimiento especial de Jigglypuff hacia abajo?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (14, 2, '¿Quién es el jefe final de Super Smash Bros Brawl?')")

            //Preguntas Deporte
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (15, 3, '¿En qué año fueron los primeros Juegos Olímpicos modernos?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (16, 3, '¿Dónde se celebraron los primeros Juegos olimpicos de la era moderna?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (17, 3, 'Deporte que hace uso de un guante de cuero, un palo de madera o metal y una pelota de cuero')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (18, 3, '¿Cual es el deporta que practica Messi de manera profesional?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (19, 3, 'El salto de la garrocha consiste en...')")

            //Preguntas Historia
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (20, 4, '¿Quién fue el primer presidente de Estados Unidos?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (21, 4, '¿Qué periodo comprende la Segunda Guerra Mundial?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (22, 4, '¿Qué emperador romano legalizó el cristianismo y puso fin a la persecución de los cristianos?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (23, 4, '¿Qué hito informático de 1969 cambiaría radicalmente el curso de la historia de la humanidad?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (24, 4, '¿Por qué es significativo el Poema de Gilgamesh?')")

            //Preguntas Varios
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (25, 5, '2 + 2 = ')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (26, 5, 'El dicho \"Mi casa es tu casa\" se usa para...')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (27, 5, '¿Cuál fue la primera consola de videojuegos?')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (28, 5, 'El termino \"Cubreme las espaldas\" se usa para...')")
            db.execSQL("INSERT INTO Pregunta (idPregunta, idCategoria, texto) VALUES (29, 5, 'En el juego UNO cuando se tiene una carta se debe decir...')")
        //endregion

            //region Opciones
            //Insertar Datos en Tabla Opciones

            //Categora Cine
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (0, 'Avengers: Endgame', 1, 0)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (1, 'Avengers: Infinity war', 0, 0)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (2, 'Avatar', 0, 0)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (3, 'Star Wars: Episodio VII - El despertar de la Fuerza', 0, 0)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (4, 'Toy story', 1, 1)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (5, 'Pequeños gigantes', 0, 1)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (6, 'Disney Pixar', 0, 1)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (7, 'Minions', 0, 1)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (8, 'Gore Verbinski', 1, 2)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (9, 'Hans Zimmer', 0, 2)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (10, 'Ted Elliott', 0, 2)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (11, 'Terry Rosio', 0, 2)")

            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (12, 'Ninguna de las anteriores', 1, 3)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (13, 'Burrito', 0, 3)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (14, 'Asno', 0, 3)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (15, 'Noble corsel', 0, 3)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (16, 'Walt Daisney Pictures', 1, 4)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (17, 'Warner Brothers', 0, 4)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (18, 'Dreamworks', 0, 4)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (19, 'Sony Pictures', 0, 4)")


            //Categoria Musica
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (20, 'Ctesibio', 1, 5)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (21, 'Sofocles', 0, 5)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (22, 'Euripides', 0, 5)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (23, 'Socrates', 0, 5)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (24, 'Chuck Berry', 1, 6)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (25, 'Elvis Presley', 0, 6)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (26, 'Bill Haley', 0, 6)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (27, 'Little Richard', 0, 6)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (28, 'Madama Butterfly', 1, 7)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (29, 'La flauta mágica', 0, 7)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (30, 'Las bodas de Fígaro', 0, 7)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (31, 'La falsa jardinera', 0, 7)")

            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (32, 'Kurt Cobain', 1, 8)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (33, 'Jim Morrison', 0, 8)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (34, 'John Lennon', 0, 8)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (35, 'Freddie Mercury', 0, 8)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (36, '¿Where are we now?', 1, 9)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (37, 'Dont Stop me now', 0, 9)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (38, 'Tie your mother down', 0, 9)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (39, 'These are the days of our lives', 0, 9)")


            //Categotia Smash
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (40, 'Masahiro Sakurai y Satoru Iwata', 1, 10)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (41, 'Satoru Iwata y Shigeru Miyamoto', 0, 10)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (42, 'Reggie y Shigeru Miyamoto', 0, 10)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (43, 'Masahiro Sakurai y Satoru Shibata', 0, 10)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (44, '12', 1, 11)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (45, '14', 0, 11)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (46, '8', 0, 11)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (47, '9', 0, 11)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (48, '25%', 1, 12)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (49, '35%', 0, 12)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (50, '50%', 0, 12)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (51, '30%', 0, 12)")


            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (52, 'Se duerme y pega un golpe fulminante', 1, 13)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (53, 'Hace un salto bomba', 0, 13)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (54, 'Usa desenrollar', 0, 13)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (55, 'Se va volando', 0, 13)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (56, 'Tabuu', 1, 14)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (57, 'Rayquaza', 0, 14)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (58, 'Master Hand', 0, 14)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (59, 'Ridley', 0, 14)")


            //Categoria Deporte
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (60, '1896', 1, 15)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (61, '1930', 0, 15)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (62, '1658', 0, 15)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (63, '2000', 0, 15)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (64, 'Atenas', 1, 16)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (65, 'Roma', 0, 16)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (66, 'Londres', 0, 16)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (67, 'Grecia', 0, 16)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (68, 'Beisbol', 1, 17)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (69, 'Basquetbol', 0, 17)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (70, 'Futbol', 0, 17)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (71, 'Cricket', 0, 17)")

            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (72, 'Futbol Soccer', 1, 18)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (73, 'Futbol americano', 0, 18)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (74, 'Hockey', 0, 18)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (75, 'Golf', 0, 18)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (76, 'Saltar para superar una barra que esta situada a gran altura', 1, 19)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (77, 'Ver quien salta mas alto', 0, 19)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (78, 'Lanzar una vara lo mas lejos posible', 0, 19)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (79, 'Equilibrase en la lanza', 0, 19)")


            //Categoria Historia
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (80, 'George Washington', 1, 20)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (81, 'Thomas Jefferson', 0, 20)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (82, 'Abraham Lincoln\n', 0, 20)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (83, 'John F. Kennedy', 0, 20)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (84, '1939 - 1945', 1, 21)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (85, '1936 - 1939', 0, 21)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (86, '1914 - 1918', 0, 21)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (87, '1950 - 1975', 0, 21)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (88, 'Constantino ', 1, 22)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (89, 'Trajano ', 0, 22)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (90, 'Adriano', 0, 22)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (91, 'Nerón ', 0, 22)")

            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (92, 'Internet', 1, 23)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (93, 'El primer ordenador personal', 0, 23)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (94, 'El primer router wi-fi', 0, 23)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (95, 'El primer iPod', 0, 23)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (96, 'Es la primera obra épica que hace referencia a la inmortalidad y la percepción humana del alma', 1, 24)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (97, 'Es el primer documento escrito sobre la adopción del monoteísmo en el Medio Oriente', 0, 24)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (98, 'Fue un libro de estrategia militar de 500 páginas que sirvió en la antigua Mesopotamia', 0, 24)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (99, 'El tratado más antiguo que existe sobre el Inframundo', 0, 24)")


            //Categoria Varios
            //Pregunta 1
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (100, '4', 1, 25)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (101, '2', 0, 25)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (102, '-4', 0, 25)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (103, '0', 0, 25)")

            //Pregunta 2
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (104, 'Invitar personas a la casa\n', 1, 26)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (105, 'Ahuyentar a la gente de la casa', 0, 26)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (106, 'Invitar a vivir a alguien a la casa', 0, 26)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (107, 'Saludar a alguien', 0, 26)")

            //Pregunta 3
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (108, 'Magnavox Odyssey', 1, 27)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (109, 'Super Nintendo', 0, 27)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (110, 'Xbox', 0, 27)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (111, 'Atari 2600', 0, 27)")

            //Pregunta 4
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (112, 'Defender su retarguardia', 1, 28)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (113, 'Tapar su espalda', 0, 28)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (114, 'Dejarlo atras', 0, 28)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (115, 'Hacer nada', 0, 28)")

            //Pregunta 5
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (116, 'UNO!', 1, 29)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (117, 'GANE', 0, 29)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (118, 'SAFE', 0, 29)")
            db.execSQL("INSERT INTO Opcion (idOpcion, texto, isCorrect, idPregunta) VALUES (119, 'LOTERIA', 0, 29)")
            //endregion

            //region Configuracion
            db.execSQL("INSERT INTO Configuracion (idConfiguracion, categoriasUsadas, numeroPreguntas, dificultad, pistasEnabled, numeroPistas) VALUES (0, 1, 5, 0, 0, 3)")
            //endregion

            //region Usuario
            db.execSQL("INSERT INTO Usuario (idUsuario, idConfiguracion, idAplicacion, imagenAvatar, userName, contraseña) VALUES (0, 0, 0, null, 'alfa', 'notiene')")
            //endregion

            //region Juego
            db.execSQL("INSERT INTO Juego (idJuego, idUsuario, estatusJuego, numPistas, cheated) VALUES (0, 0, 0,0,0)")
            //endregion

            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }
}