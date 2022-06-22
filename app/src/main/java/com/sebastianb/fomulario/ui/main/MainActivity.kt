package com.sebastianb.fomulario.ui.main

import android.annotation.SuppressLint

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle

import android.widget.*
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.ViewModelProvider
import com.sebastianb.fomulario.R

import com.sebastianb.fomulario.databinding.ActivityMainBinding
import java.util.*



class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel



    private var fechanacimiento: String = ""
    private var cal = Calendar.getInstance()




    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)



        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]


        mainViewModel.fieldError.observe(this) { err ->
            val toast = Toast.makeText(this, err, Toast.LENGTH_SHORT)
            toast.show()
        }

        mainViewModel.data.observe(this) { data ->
            mainBinding.infoTextView.text = data
        }

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
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


        with(mainBinding) {
            registerButton.setOnClickListener {
                val name = nameTextFile.text.toString()
                val email = emailEditText.text.toString()
                val password = passEditText.text.toString()
                val confirmPassword = pass2EditText.text.toString()
                val date = dateEditText.text.toString()
                val ciudad = mainBinding.ciudadNacimientoSpinner.selectedItem.toString()

                val masculino = mainBinding.masculinoBotton.isChecked
                val femenino = mainBinding.femeninoBotton.isChecked

                val viajar = mainBinding.viajarCheckbox.isChecked
                val leer = mainBinding.leerCheckbox.isChecked
                val pintar = mainBinding.pintarCheckbox.isChecked
                val bailar = mainBinding.bailarCheckbox.isChecked






                mainViewModel.register(name, email, password, confirmPassword, date, ciudad, masculino, femenino, viajar, leer, pintar, bailar)
            }
        }


    }




}
//modificacion
//v2.0
//v2.0