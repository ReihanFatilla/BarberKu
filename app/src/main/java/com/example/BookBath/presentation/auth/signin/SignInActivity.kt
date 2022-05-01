package com.example.BookBath.presentation.auth.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.BookBath.databinding.ActivitySignInBinding
import com.example.BookBath.presentation.auth.signup.SignUpActivity
import com.example.BookBath.presentation.viewmodel.UserViewModel

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        initView()
    }

    private fun initView() {
        binding.apply{
            tbSignUp.setOnClickListener {
                startActivity(Intent(this@SignInActivity, SignUpActivity::class.java))
                finish()
            }

            btnLogin.setOnClickListener {
                login()
            }
        }
    }

    private fun login() {
        val username = binding.edtUsername.text.toString()
        val password = binding.edtPassword.text.toString()

        if(username.isEmpty() || password.isEmpty()) {
            binding.apply {
                edtUsername.error = "Username and Password is required"
                edtPassword.error = "Username and Password is required"
            }
        } else {
//            viewModel.loginUser(this , binding , username, password)
        }
    }
}