package com.sebastianb.fomulario.ui.main

import android.widget.CheckBox
import android.widget.RadioButton
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _data : MutableLiveData<String> = MutableLiveData()
    val data : LiveData<String> = _data

    private val _fieldError : MutableLiveData<String> = MutableLiveData()
    val fieldError : LiveData<String> = _fieldError

    fun register (name:String, email:String, password:String, confirmPassword:String,
                  date:String, ciudad:String, masculino:Boolean, femenino:Boolean,
                  viajar:Boolean, leer:Boolean, pintar:Boolean, bailar: Boolean) {

        if (name.isEmpty()) {
            _fieldError.value = "El nombre es requerido"
            return
        }

        if (email.isEmpty()) {
            _fieldError.value = "El email es requerido"
            return
        }

        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            _fieldError.value = "El email no es válido"
            return
        }

        if (password.isEmpty()) {
            _fieldError.value = "La contraseña es requerida"
            return
        }

        if (confirmPassword.isEmpty()) {
            _fieldError.value = "La confirmación de contraseña es requerida"
            return
        }

        if (password != confirmPassword) {
            _fieldError.value = "Las contraseñas no coinciden"
            return
        }

        if (date.isEmpty()) {
            _fieldError.value = "La fecha de nacimiento es requerida"
            return
        }


        if (ciudad.isEmpty()) {
            _fieldError.value = "La ciudad es requerida"
            return
        }


        _data.value =
            "Nombre: $name\n" + "Email: $email\n" + "Contraseña: $password\n" + "Confirmación de contraseña: $confirmPassword\n" + "Fecha de nacimiento: $date\n" + "Ciudad: $ciudad\n" + "Sexo: ${if (masculino) "Masculino" else "Femenino"}\n" + "Preferencias: ${if (viajar) "Viajar" else ""} ${if (leer) "Leer" else ""} ${if (pintar) "Pintar" else ""} ${if (bailar) "Bailar" else ""}"

    }

}
