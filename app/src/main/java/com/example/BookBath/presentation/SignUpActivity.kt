package com.example.BookBath.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.BookBath.data.room.User
import com.example.BookBath.databinding.ActivitySignUpBinding
import com.example.BookBath.presentation.viewmodel.UserViewModel

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        iniView()
    }

    private fun iniView() {
        binding.apply{
            tbSignUp.setOnClickListener {
                startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                finish()
            }
            btnRegister.setOnClickListener{
                register()
            }

        }
    }

    private fun register() {
        binding.apply{
            val username = edtUsername.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            val userRegister = User(
                0,
                username,
                email,
                password
            )

            viewModel.registerUser(userRegister)
            startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
            Toast.makeText(this@SignUpActivity, "Register Success", Toast.LENGTH_SHORT).show()
            finish()

        }
    }
}