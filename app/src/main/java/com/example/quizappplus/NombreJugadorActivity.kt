package com.example.quizappplus

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

const val EXTRA_RESULT_TEXT="com.example.quizappplus.EXTRA_RESULT_TEXT"
const val NOMBREJUGADOR_ACTIVITY_REQUEST_CODE=1100



class NombreJugadorActivity :AppCompatActivity(){

    private lateinit var nombreEditText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nombrejugador)

        nombreEditText = findViewById(R.id.Nombre_EditText)

        nombreEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val intent: Intent = Intent()
                intent.putExtra(EXTRA_RESULT_TEXT,nombreEditText.text.toString())
                setResult(Activity.RESULT_OK,intent)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
}