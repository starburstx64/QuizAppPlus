package com.example.quizappplus

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

const val CONFIGURACIONES_REQUEST_CODE=1000

class ConfiguracionesActivity : AppCompatActivity() {

    //region ControlesVista
    //CheckBoxes
    private lateinit var todosCheckBox:CheckBox
    private lateinit var cineCheckBox:CheckBox
    private lateinit var musicaCheckBox:CheckBox
    private lateinit var deporteCheckBox:CheckBox
    private lateinit var historiaCheckBox:CheckBox
    private lateinit var smashCheckBox:CheckBox
    private lateinit var variosCheckBox:CheckBox

    //Spinners
    private lateinit var numPreguntasSpinner:Spinner
    private lateinit var numPistasSpinner:Spinner

    //RadioButtons
    private lateinit var facilRadioButton:RadioButton
    private lateinit var medioRadioButton:RadioButton
    private lateinit var dificilRadioButton:RadioButton

    //Switchs
    private lateinit var habilitarPistasSwitch:Switch

    //Buttons
    //private lateinit var volverButton:Button
    //endregion

    private lateinit var model:ConfiguracionesVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuraciones)

        //region InicializadorControles
        todosCheckBox=findViewById(R.id.todos_checkbox)
        cineCheckBox=findViewById(R.id.cine_checkbox)
        musicaCheckBox=findViewById(R.id.musica_checkbox)
        deporteCheckBox=findViewById(R.id.deportes_checkbox)
        historiaCheckBox=findViewById(R.id.historia_checkbox)
        smashCheckBox=findViewById(R.id.smash_checkbox)
        variosCheckBox=findViewById(R.id.varios_checkbox)

        numPreguntasSpinner=findViewById(R.id.spinner_preguntas)
        numPistasSpinner=findViewById(R.id.spinner_pistas)

        facilRadioButton=findViewById(R.id.facil_radio_button)
        medioRadioButton=findViewById(R.id.medio_radio_button)
        dificilRadioButton=findViewById(R.id.dificil_radio_button)

        habilitarPistasSwitch=findViewById(R.id.enable_switch_pistas)

        //volverButton=findViewById(R.id.volver_Button)
        //endregion
        //lista de checkboxes
        val checkBoxes= listOf<CheckBox>(
            cineCheckBox,
            musicaCheckBox
        )

        //Asignar el modelo
        model = intent.getSerializableExtra("EXTRA_CONFIGURACINES_VIEWMODEL") as ConfiguracionesVM
        //checarEnvio(model)

        //Asignar las configuraciones
        for(i in 0..model.categorias.size-1)
        {
            if(model.categorias[i].seleccionada==true)
            {
                checkBoxes[i].isChecked=true
            }
        }

        //Modificaciones
        cineCheckBox.setOnClickListener{
            ActualizarVM()
            val intent: Intent = Intent()
            intent.putExtra("EXTRA_RESULT_CONFIUGRACION_VIEWMODEL",model)
            setResult(Activity.RESULT_OK,intent)
        }
        /*volverButton.setOnClickListener{
            finishActivity(1)
        }*/

    }

    override fun onPause() {
        super.onPause()

    }
    private fun ActualizarVM()
    {
        model.categorias[0].seleccionada=cineCheckBox.isChecked
    }

    private fun checarEnvio(model:ConfiguracionesVM?){
        val respuesta: Toast =
            if(model != null){
                Toast.makeText(
                    this,
                    "CORRECTO",
                    Toast.LENGTH_SHORT
                )
            } else{
                Toast.makeText(
                    this,
                    "INCORRECTO",
                    Toast.LENGTH_SHORT
                )
            }
        respuesta.show()
    }
}
