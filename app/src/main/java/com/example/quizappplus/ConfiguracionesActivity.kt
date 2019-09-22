package com.example.quizappplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Switch

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
    //endregion

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
        //endregion
    }
}
