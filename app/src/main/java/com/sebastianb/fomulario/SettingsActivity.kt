package com.sebastianb.fomulario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sebastianb.fomulario.databinding.ActivitySettingsBinding
import com.sebastianb.fomulario.databinding.ActivitySplashBinding

private lateinit var settingsBinding: ActivitySettingsBinding
class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view= settingsBinding.root
        setContentView(view)
    }
}