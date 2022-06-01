package com.sebastianb.fomulario

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.widget.*
import androidx.core.view.isEmpty
import com.sebastianb.fomulario.databinding.ActivityMainBinding
import java.util.*
import java.util.jar.Attributes


class MainActivity : Activity() {     // los dos puntos son herencia

    private lateinit var mainBinding: ActivityMainBinding  //Declaramos pero no la inicializamos
    private var fechanacimiento:String=""
    private var cal=Calendar.getInstance()
    @SuppressLint("SetTextI18n")
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
        val ciudad_nacimiento:Spinner=findViewById(R.id.ciudad_nacimiento_spinner)
        val dateSetListener=DatePickerDialog.OnDateSetListener {
            view, year, month, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, month)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val formato = SimpleDateFormat("dd/MM/yyyy", Locale.US)
            fechanacimiento = formato.format(cal.time).toString()
            mainBinding.dateEditText.setText(fechanacimiento)
            }

        mainBinding.dateEditText.setOnClickListener {

            DatePickerDialog(this,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
         }



        var hobbies = ""
        mainBinding.registerButton.setOnClickListener{
            if(mainBinding.emailEditText.text?.isEmpty() == true || mainBinding.nameTextFile.text?.isEmpty()==true || mainBinding.passEditText.text?.isEmpty()==true || mainBinding.pass2EditText.text?.isEmpty()==true || mainBinding.dateEditText.text?.isEmpty()==true) {
                Toast.makeText(this, "Debe ingresar todos los campos", Toast.LENGTH_SHORT).show()

                if (mainBinding.emailEditText.text?.isEmpty() == true)
                    mainBinding.emailEditText.error = "Debe ingresar email"

                if(mainBinding.passEditText.text?.isEmpty() ==true)
                    mainBinding.passEditText.error = "Debe ingresar contraseña"

                if(mainBinding.pass2EditText.text?.isEmpty() ==true)
                    mainBinding.pass2EditText.error = "Debe ingresar contraseña"

                if (mainBinding.nameTextFile.text?.isEmpty() == true)
                    mainBinding.nameTextFile.error = "Digite nombre"

                if(mainBinding.dateEditText.text?.isEmpty()==true)
                    mainBinding.dateEditText.error= "Digite fecha"
            }


            else {
                val genero= if(masculinoRadioButton.isChecked)getString(R.string.masculino)else getString(R.string.femenino)

                if (viajarCheckBox.isChecked) hobbies = getString(R.string.viajar)
                if (bailarCheckBox.isChecked) hobbies = hobbies + " " + getString(R.string.bailar)
                if (pintarCheckBox.isChecked) hobbies = hobbies + " " + getString(R.string.pintar)
                if (leerCheckBox.isChecked) hobbies = hobbies + " " + getString(R.string.leer)
                val ciudad=ciudad_nacimiento.selectedItem.toString()

                val nombre = mainBinding.nameTextFile.text.toString()
                val email = mainBinding.emailEditText.text.toString()
                val pass=mainBinding.passEditText.text.toString()
                val pass2=mainBinding.pass2EditText.text.toString()
                if (pass!=pass2){
                    Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_SHORT).show()
                    mainBinding.pass2EditText.error = "No coincide la contraseña"

                }
                else{
                    mainBinding.infoTextView.text = nombre + "\n" + email + "\n"+ pass+"\n" +genero+"\n"+ciudad+"\n"+ hobbies +"\n" +fechanacimiento}
            }

        }
    }



}
//modificacion