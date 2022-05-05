package com.example.BarberKu.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.BarberKu.R
import com.example.BarberKu.presentation.auth.signin.SignInActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, SignInActivity::class.java))
            overridePendingTransition(R.anim.fade_out, R.anim.fade_in)
            finish()
        }, 750)
    }
}