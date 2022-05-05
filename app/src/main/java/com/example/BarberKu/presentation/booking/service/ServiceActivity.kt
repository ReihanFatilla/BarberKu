package com.example.BarberKu.presentation.booking.service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.BarberKu.databinding.ActivityServiceBinding
import com.example.BarberKu.presentation.booking.location.LocationActivity

class ServiceActivity : AppCompatActivity() {

    private var _binding: ActivityServiceBinding? = null
    private val binding get() = _binding as ActivityServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {

        binding.apply{
            btnNext.setOnClickListener {
                startActivity(Intent(this@ServiceActivity, LocationActivity::class.java))
            }

            btnBack.setOnClickListener {
                finish()
            }
        }

    }
}