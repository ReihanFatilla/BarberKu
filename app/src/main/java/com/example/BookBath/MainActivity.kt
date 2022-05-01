package com.example.BookBath

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.BookBath.data.sharedpref.LoginPreference
import com.example.BookBath.databinding.ActivityMainBinding
import com.example.BookBath.presentation.auth.signin.SignInActivity
import java.util.prefs.Preferences

class MainActivity : AppCompatActivity() {

    private lateinit var preferences: LoginPreference

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = LoginPreference(this)

        initView()
    }

    private fun initView() {
        binding.apply{
            tvName.text = preferences.getName(LoginPreference.PREF_NAME)

            tvLogout.setOnClickListener {
                startActivity(Intent(this@MainActivity, SignInActivity::class.java))
                preferences.logout()
            }
        }
    }
}