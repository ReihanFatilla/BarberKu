package com.example.BarberKu.presentation.booking.booker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.BarberKu.databinding.ActivityBookerBinding
import com.example.BarberKu.presentation.booking.service.ServiceActivity

class BookerActivity : AppCompatActivity() {

    private var _binding: ActivityBookerBinding? = null
    private val binding get() = _binding as ActivityBookerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBookerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.apply {
            btnNext.setOnClickListener {
                startActivity(Intent(this@BookerActivity, ServiceActivity::class.java))
            }

            btnBack.setOnClickListener {
                finish()
            }

        }
    }
}