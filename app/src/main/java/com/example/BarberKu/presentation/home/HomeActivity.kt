package com.example.BarberKu.presentation.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.BarberKu.data.local.sharedpref.LoginPreference
import com.example.BarberKu.databinding.ActivityHomeBinding
import com.example.BarberKu.presentation.auth.signin.SignInActivity
import com.example.BarberKu.presentation.booking.booker.BookerActivity
import com.reift.weatherapp.helper.HelperFunction.transparentStatusbar

class HomeActivity : AppCompatActivity() {

    private lateinit var preferences: LoginPreference

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding as ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transparentStatusbar(this)
        preferences = LoginPreference(this)

        initView()
    }

    private fun initView() {
        binding.apply{
            tvName.text = preferences.getName(LoginPreference.PREF_NAME)

            tvLogout.setOnClickListener {
                finishAffinity()
                startActivity(Intent(this@HomeActivity, SignInActivity::class.java))
                preferences.logout()
            }

            btnAddBooking.setOnClickListener {
                startActivity(Intent(this@HomeActivity, BookerActivity::class.java))
            }
        }
    }
}