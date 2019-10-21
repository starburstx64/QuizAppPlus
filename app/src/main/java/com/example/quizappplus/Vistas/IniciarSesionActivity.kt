package com.example.quizappplus.Vistas

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.quizappplus.DB.AppDatabase
import com.example.quizappplus.R
import com.example.quizappplus.VistaModelos.IniciarSesionVM

class IniciarSesionActivity : AppCompatActivity() {

    private lateinit var usuario_edtiText_inicioSesion : EditText
    private lateinit var password_textView_InicioSesion : EditText
    private lateinit var iniciarSesion_button_IniciarSesion : Button
    private lateinit var clickAqui_textView_InicioSesion : TextView

    private val model by lazy { ViewModelProviders.of(this)[IniciarSesionVM::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        usuario_edtiText_inicioSesion = findViewById(R.id.usuario_edtiText_inicioSesion)
        password_textView_InicioSesion = findViewById(R.id.password_edtiText_inicioSesion)
        iniciarSesion_button_IniciarSesion = findViewById(R.id.iniciarSesion_button_IniciarSesion)
        clickAqui_textView_InicioSesion = findViewById(R.id.clickAqui_textView_InicioSesion)

        iniciarSesion_button_IniciarSesion.setOnClickListener {
            if (validarInputs()) {
                val mainIntent = Intent(this, MenuPrincipalAcrivity::class.java)
                finish()
            }

            else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        usuario_edtiText_inicioSesion.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                model.userNameText = p0?.toString() ?: ""
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                model.userNameText = p0?.toString() ?: ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                model.userNameText = p0?.toString() ?: ""
            }
        })

        password_textView_InicioSesion.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                model.userPasswordText = p0?.toString() ?: ""
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                model.userPasswordText = p0?.toString() ?: ""
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                model.userPasswordText = p0?.toString() ?: ""
            }
        })

        clickAqui_textView_InicioSesion.setOnClickListener {
            val registroIntent = Intent(this, RegistrarUsuario::class.java)
            startActivity(registroIntent)
        }

        model.inicializar(AppDatabase.getAppDatabase(this))

        // restablecer conf
        usuario_edtiText_inicioSesion.setText(model.userNameText)
        password_textView_InicioSesion.setText(model.userPasswordText)
    }

    /**
     * @brief Retorna true si los campos usuario y password son validos
     */
    private fun validarInputs() : Boolean {
        if (usuario_edtiText_inicioSesion.text.isBlank() || password_textView_InicioSesion.text.isBlank()) {
            return false
        }

        return model.tryLogin()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        finishAffinity()
    }
}
