package com.example.quizappplus

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_configuraciones.*

const val CONFIGURACIONES_REQUEST_CODE = 1000

class ConfiguracionesActivity : AppCompatActivity() {

    //region ControlesVista
    //CheckBoxes
    private lateinit var todosCheckBox: CheckBox
    private lateinit var cineCheckBox: CheckBox
    private lateinit var musicaCheckBox: CheckBox
    private lateinit var deporteCheckBox: CheckBox
    private lateinit var historiaCheckBox: CheckBox
    private lateinit var smashCheckBox: CheckBox
    private lateinit var variosCheckBox: CheckBox

    //Spinners
    private lateinit var numPreguntasSpinner: Spinner
    private lateinit var numPistasSpinner: Spinner

    //RadioButtons
    private lateinit var dificultadRadioGroup: RadioGroup
    private lateinit var facilRadioButton: RadioButton
    private lateinit var medioRadioButton: RadioButton
    private lateinit var dificilRadioButton: RadioButton

    //Switchs
    private lateinit var habilitarPistasSwitch: Switch

    //Buttons
    //private lateinit var volverButton:Button
    //endregion

    private lateinit var model: ConfiguracionesVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuraciones)

        //region InicializadorControles
        todosCheckBox = findViewById(R.id.todos_checkbox)
        cineCheckBox = findViewById(R.id.cine_checkbox)
        musicaCheckBox = findViewById(R.id.musica_checkbox)
        deporteCheckBox = findViewById(R.id.deportes_checkbox)
        historiaCheckBox = findViewById(R.id.historia_checkbox)
        smashCheckBox = findViewById(R.id.smash_checkbox)
        variosCheckBox = findViewById(R.id.varios_checkbox)

        numPreguntasSpinner = findViewById(R.id.spinner_preguntas)
        numPistasSpinner = findViewById(R.id.spinner_pistas)

        dificultadRadioGroup = findViewById(R.id.dificultad_Radiogroup)
        facilRadioButton = findViewById(R.id.facil_radio_button)
        medioRadioButton = findViewById(R.id.medio_radio_button)
        dificilRadioButton = findViewById(R.id.dificil_radio_button)

        habilitarPistasSwitch = findViewById(R.id.enable_switch_pistas)

        //volverButton=findViewById(R.id.volver_Button)
        //endregion
        //lista de checkboxes
        val checkBoxes = listOf<CheckBox>(
            cineCheckBox,
            musicaCheckBox,
            smashCheckBox,
            deporteCheckBox,
            historiaCheckBox,
            variosCheckBox
        )

        //Asignar el modelo
        model = intent.getSerializableExtra("EXTRA_CONFIGURACINES_VIEWMODEL") as ConfiguracionesVM
        //checarEnvio(model)

        //region Asignar las configuraciones
        for (i in 0..model.categorias.size - 1) {
            if (model.categorias[i].seleccionada == true) {
                checkBoxes[i].isChecked = true
            }
        }
        habilitarPistasSwitch.isChecked = model.pistas
        spinner_pistas.isEnabled = habilitarPistasSwitch.isChecked
        spinner_preguntas.setSelection(model.numPregunta - 5)
        spinner_pistas.setSelection(model.numPistas - 1)
        ChecarRadioButton(model.dificultad)
        //endregion

        //region Modificaciones
        cineCheckBox.setOnClickListener {
            model.categorias[0].seleccionada = cineCheckBox.isChecked
            ActualizarVM()
        }
        musicaCheckBox.setOnClickListener {
            model.categorias[1].seleccionada = musicaCheckBox.isChecked
            ActualizarVM()
        }
        smashCheckBox.setOnClickListener{
            model.categorias[2].seleccionada=smashCheckBox.isChecked
            ActualizarVM()
        }
        deporteCheckBox.setOnClickListener{
            model.categorias[3].seleccionada=deporteCheckBox.isChecked
            ActualizarVM()
        }
        historiaCheckBox.setOnClickListener{
            model.categorias[4].seleccionada=historiaCheckBox.isChecked
            ActualizarVM()
        }
        variosCheckBox.setOnClickListener{
            model.categorias[5].seleccionada=variosCheckBox.isChecked
            ActualizarVM()
        }
        habilitarPistasSwitch.setOnClickListener {
            spinner_pistas.isEnabled = habilitarPistasSwitch.isChecked
            model.pistas = habilitarPistasSwitch.isChecked
            ActualizarVM()
        }
        spinner_preguntas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(var1: AdapterView<*>, var2: View, position: Int, var4: Long) {
                model.numPregunta = spinner_preguntas.selectedItem.toString().toInt()
                ActualizarVM()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        spinner_pistas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(var1: AdapterView<*>, var2: View, position: Int, var4: Long) {
                model.numPistas = spinner_pistas.selectedItem.toString().toInt()
                ActualizarVM()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
        facilRadioButton.setOnClickListener{
            model.dificultad=1
            ActualizarVM()
        }
        medioRadioButton.setOnClickListener{
            model.dificultad=2
            ActualizarVM()
        }
        dificilRadioButton.setOnClickListener{
            model.dificultad=3
            ActualizarVM()
        }
        //endregion
        /*volverButton.setOnClickListener{
            finishActivity(1)
        }*/

    }

    private fun ActualizarVM() {
        val intent: Intent = Intent()
        intent.putExtra("EXTRA_RESULT_CONFIUGRACION_VIEWMODEL", model)
        setResult(Activity.RESULT_OK, intent)
    }

    private fun ChecarRadioButton(id: Int) {
        when (id) {
            1 -> facilRadioButton.isChecked = true
            2 -> medioRadioButton.isChecked = true
            3 -> dificilRadioButton.isChecked = true
        }
    }

    private fun checarEnvio(model: ConfiguracionesVM?) {
        val respuesta: Toast =
            if (model != null) {
                Toast.makeText(
                    this,
                    "CORRECTO",
                    Toast.LENGTH_SHORT
                )
            } else {
                Toast.makeText(
                    this,
                    "INCORRECTO",
                    Toast.LENGTH_SHORT
                )
            }
        respuesta.show()
    }
}
