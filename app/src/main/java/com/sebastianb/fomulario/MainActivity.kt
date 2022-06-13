package com.sebastianb.fomulario

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle

import android.widget.*
import androidx.core.util.PatternsCompat

import com.sebastianb.fomulario.databinding.ActivityMainBinding
import java.util.*



class MainActivity : Activity() {     // los dos puntos son herencia

    private lateinit var mainBinding: ActivityMainBinding  //Declaramos pero no la inicializamos
    private var fechanacimiento: String = ""
    private var cal = Calendar.getInstance()
    private var conf=0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        val masculinoRadioButton: RadioButton = findViewById(R.id.masculino_botton)

        val viajarCheckBox: CheckBox = findViewById(R.id.viajar_checkbox)
        val bailarCheckBox: CheckBox = findViewById(R.id.bailar_checkbox)
        val pintarCheckBox: CheckBox = findViewById(R.id.pintar_checkbox)
        val leerCheckBox: CheckBox = findViewById(R.id.leer_checkbox)
        val ciudad_nacimiento: Spinner = findViewById(R.id.ciudad_nacimiento_spinner)
        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            fechanacimiento = formato.format(cal.time).toString()
            mainBinding.dateEditText.setText(fechanacimiento)
        }

        mainBinding.dateEditText.setOnClickListener {

            DatePickerDialog(
                this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }


        var hobbies = ""
        mainBinding.registerButton.setOnClickListener {validar()
            if (mainBinding.emailEditText.text?.isEmpty() == true || mainBinding.nameTextFile.text?.isEmpty() == true || mainBinding.passEditText.text?.isEmpty() == true || mainBinding.pass2EditText.text?.isEmpty() == true || mainBinding.dateEditText.text?.isEmpty() == true||conf==1) {
                Toast.makeText(this, getString(R.string.ingresar_campos), Toast.LENGTH_SHORT).show()
                mainBinding.infoTextView.text=null
                if (mainBinding.emailEditText.text?.isEmpty() == true)
                    mainBinding.emailEditText.error = getString(R.string.ingresar_email)



                if (mainBinding.nameTextFile.text?.isEmpty() == true)
                    mainBinding.nameTextFile.error = getString(R.string.digite_nombre)

                if (mainBinding.dateEditText.text?.isEmpty() == true)
                    mainBinding.dateEditText.error = getString(R.string.digite_fecha)
            } else {
                val genero =
                    if (masculinoRadioButton.isChecked) getString(R.string.masculino) else getString(
                        R.string.femenino
                    )

                if (viajarCheckBox.isChecked) hobbies = getString(R.string.viajar)
                if (bailarCheckBox.isChecked) hobbies = hobbies + " " + getString(R.string.bailar)
                if (pintarCheckBox.isChecked) hobbies = hobbies + " " + getString(R.string.pintar)
                if (leerCheckBox.isChecked) hobbies = hobbies + " " + getString(R.string.leer)
                val ciudad = ciudad_nacimiento.selectedItem.toString()

                val nombre = mainBinding.nameTextFile.text.toString()
                val email = mainBinding.emailEditText.text.toString()
                val pass = mainBinding.passEditText.text.toString()
                val pass2 = mainBinding.pass2EditText.text.toString()
                if (pass != pass2) {
                    Toast.makeText(this, getString(R.string.contraseña_no_coincide), Toast.LENGTH_SHORT).show()
                    mainBinding.pass2EditText.error = getString(R.string.contraseña_no_coincide)
                    mainBinding.infoTextView.text=null

                } else {
                    mainBinding.infoTextView.text = getString(
                        R.string.info,
                        nombre,
                        email,
                        pass,
                        genero,
                        hobbies,
                        ciudad,
                        fechanacimiento
                    )
                }

            }

        }
    }

    private fun validar_email(): Boolean {

        val emailv = mainBinding.emailTextInputLayout.editText?.text.toString()
        return if (emailv.isEmpty()) {
            mainBinding.emailEditText.error = getString(R.string.vacio)

            false

        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(emailv).matches()) {
            mainBinding.emailEditText.error = getString(R.string.correo_valido)

            false
        } else {
            mainBinding.emailEditText.error = null
            true
        }
    }

    private fun validar_pass(): Boolean {

        val contrasena = mainBinding.passwordTextInput.editText?.text.toString()
        val contrasena2=mainBinding.passwordAgainTextInput.editText?.text.toString()
        return if (contrasena.isEmpty()||contrasena2.isEmpty()) {
            if (contrasena2.isEmpty()){mainBinding.passwordAgainTextInput.error = getString(R.string.vacio)
                false}
            if (contrasena.isEmpty()){ mainBinding.passwordTextInput.error=getString(R.string.vacio)
                false}

            false


        }
        else {
            if(mainBinding.passEditText.text.toString() != mainBinding.pass2EditText.text.toString() && contrasena.isEmpty()==false && contrasena2.isEmpty()==false ){
                mainBinding.passwordAgainTextInput.error = getString(R.string.contraseña_no_coincide)
                mainBinding.passwordTextInput.error = null
                true


            }
            else{
                mainBinding.passwordTextInput.error = null
                mainBinding.passwordAgainTextInput.error = null
                true

            }
        }
    }
    private fun validar(){
        val resultado= arrayOf(validar_email(),validar_pass())
        if(false in resultado){
            conf=1
            return
        }
        conf=0
        Toast.makeText(this,getString(R.string.datos_registrados),Toast.LENGTH_SHORT).show()

    }
}
//modificacion