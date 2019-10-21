package com.example.quizappplus.Vistas

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.example.quizappplus.R
import com.example.quizappplus.VistaModelos.RegistrarUsuarioVM
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*

class RegistrarUsuario : AppCompatActivity() {

    private lateinit var imgselect1_imageButton_registrarUsuiario : ImageButton
    private lateinit var imgselect2_imageButton_registrarUsuiario : ImageButton
    private lateinit var imgselect3_imageButton_registrarUsuiario : ImageButton
    private lateinit var imgselect4_imageButton_registrarUsuiario : ImageButton
    private lateinit var imgselect5_imageButton_registrarUsuiario : ImageButton

    private lateinit var Usuario_editView_registrarUsuario : EditText
    private lateinit var password_editView_registrarUsuario : EditText

    private lateinit var terminarRegistro_button_registrarUsuario : Button

    private lateinit var waifuList : MutableList<ImageButton>

    private val model by lazy { ViewModelProviders.of(this)[RegistrarUsuarioVM::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_usuario)

        imgselect1_imageButton_registrarUsuiario = findViewById(R.id.imgselect1_imageButton_registrarUsuiario)
        imgselect2_imageButton_registrarUsuiario = findViewById(R.id.imgselect2_imageButton_registrarUsuiario)
        imgselect3_imageButton_registrarUsuiario = findViewById(R.id.imgselect3_imageButton_registrarUsuiario)
        imgselect4_imageButton_registrarUsuiario = findViewById(R.id.imgselect4_imageButton_registrarUsuiario)
        imgselect5_imageButton_registrarUsuiario = findViewById(R.id.imgselect5_imageButton_registrarUsuiario)

        Usuario_editView_registrarUsuario = findViewById(R.id.Usuario_editView_registrarUsuario)
        password_editView_registrarUsuario = findViewById(R.id.password_editView_registrarUsuario)

        waifuList = mutableListOf(imgselect1_imageButton_registrarUsuiario, imgselect2_imageButton_registrarUsuiario,
                imgselect3_imageButton_registrarUsuiario, imgselect4_imageButton_registrarUsuiario, imgselect5_imageButton_registrarUsuiario)

        terminarRegistro_button_registrarUsuario = findViewById(R.id.terminarRegistro_button_registrarUsuario)

        val waifuOnClickEvent = View.OnClickListener {

            for (waffle in waifuList) {
                waffle.isEnabled = true
                waffle.alpha = 1f
            }

            it.isEnabled = false
            it.alpha = 0.5f

            model.selectedWaifu = it.id
        }

        for (waffle in waifuList) {
            waffle.setOnClickListener(waifuOnClickEvent)

            if (waffle.id == model.selectedWaifu) {
                waffle.alpha = 0.5f
            }

            else {
                waffle.alpha = 1.0f
            }
        }

        Usuario_editView_registrarUsuario.addTextChangedListener(object : TextWatcher {
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

        password_editView_registrarUsuario.addTextChangedListener(object : TextWatcher {
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

        terminarRegistro_button_registrarUsuario.setOnClickListener {
            if (validarInputs()) {

                Toast.makeText(this, "Registro Exitoso!", Toast.LENGTH_SHORT).show()

                val toLoginIntent = Intent(this, IniciarSesionActivity::class.java)
                startActivity(toLoginIntent)
            }

            else {
                // Mostramos un alert dialog
                val message = if (Usuario_editView_registrarUsuario.text.isBlank() || password_editView_registrarUsuario.text.isBlank() || model.selectedWaifu == 0)
                    "Favor de llenar todos los campos"
                else
                    "Nombre de usuario no disponible"

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage(message)

                builder.setPositiveButton("Ok") { _, _ ->

                }

                builder.show()
            }
        }

        Usuario_editView_registrarUsuario.setText(model.userNameText)
        password_editView_registrarUsuario.setText(model.userPasswordText)
    }

    private fun validarInputs() : Boolean {

        if (Usuario_editView_registrarUsuario.text.isBlank() || password_editView_registrarUsuario.text.isBlank()) {
            return false
        }

        return model.tryRegistrarUsuario()
    }
}
