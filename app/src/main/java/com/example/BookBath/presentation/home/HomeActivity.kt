package com.example.BookBath.presentation.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.BookBath.data.local.sharedpref.LoginPreference
import com.example.BookBath.databinding.ActivityHomeBinding
import com.example.BookBath.presentation.auth.signin.SignInActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var preferences: LoginPreference

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding as ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = LoginPreference(this)

        initView()
    }

    private fun initView() {
        binding.apply{
            tvName.text = preferences.getName(LoginPreference.PREF_NAME)

            tvLogout.setOnClickListener {
                startActivity(Intent(this@HomeActivity, SignInActivity::class.java))
                preferences.logout()
            }
        }
    }
}