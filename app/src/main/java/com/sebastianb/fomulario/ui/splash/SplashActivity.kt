package com.sebastianb.fomulario.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sebastianb.fomulario.databinding.ActivitySplashBinding
import com.sebastianb.fomulario.ui.main.MainActivity
import java.util.*
import kotlin.concurrent.timerTask

@SuppressLint("StaticFieldLeak")
private lateinit var splashBinding:ActivitySplashBinding
@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        val view = splashBinding.root
        setContentView(view)
        val timer= Timer()


        timer.schedule(timerTask {
            goToMainActivity()
        },1000
        )
    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}