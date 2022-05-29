package com.sebastianb.fomulario

import android.app.Activity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.CheckBox
import android.widget.RadioButton
import com.sebastianb.fomulario.databinding.ActivityMainBinding

class MainActivity : Activity() {     // los dos puntos son herencia

    private lateinit var mainBinding: ActivityMainBinding  //Declaramos pero no la inicializamos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        val view= mainBinding.root
        setContentView(view)
        val masculinoRadioButton: RadioButton =findViewById(R.id.masculino_botton)
        val femeninoRadioButton: RadioButton=findViewById(R.id.femenino_botton)
        val viajarCheckBox: CheckBox =findViewById(R.id.viajar_checkbox)
        val bailarCheckBox: CheckBox=findViewById(R.id.bailar_checkbox)
        val pintarCheckBox: CheckBox=findViewById(R.id.pintar_checkbox)
        val leerCheckBox: CheckBox=findViewById(R.id.leer_checkbox)
        mainBinding.registerButton.setOnClickListener{
            var genero:String
            var hobbies=""
            if (masculinoRadioButton.isChecked)
                genero =getString(R.string.masculino)
            if (femeninoRadioButton.isChecked)
                genero=getString(R.string.femenino)
            if (viajarCheckBox.isChecked) hobbies=getString(R.string.viajar)
            if (bailarCheckBox.isChecked) hobbies=hobbies +" "+getString(R.string.bailar)
            if (pintarCheckBox.isChecked) hobbies=hobbies +" "+getString(R.string.pintar)
            if (leerCheckBox.isChecked) hobbies=getString(R.string.leer)



            val email=mainBinding.emailEditText.text.toString()
            mainBinding.infoTextView.text="\n" + email
        }
    }

}
//modificacion