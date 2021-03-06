package com.example.BarberKu.presentation.auth.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import com.example.BarberKu.presentation.home.HomeActivity
import com.example.BarberKu.data.remote.firebase.User
import com.example.BarberKu.data.local.sharedpref.LoginPreference
import com.example.BarberKu.databinding.ActivitySignInBinding
import com.example.BarberKu.presentation.auth.signup.SignUpActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.reift.weatherapp.helper.HelperFunction

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding


    private lateinit var mDatabasePreference: DatabaseReference
    private lateinit var preferences: LoginPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this);
        HelperFunction.transparentStatusbar(this)

        mDatabasePreference = FirebaseDatabase.getInstance().getReference("User")
        preferences = LoginPreference(this)


        passwordShowHide()
        initView()
    }

    private fun passwordShowHide() {
        binding.apply {
            btnShowHide.setOnClickListener {
                if(edtPassword.transformationMethod == PasswordTransformationMethod.getInstance()){
                    edtPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else {
                    edtPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(preferences.getLoginStatus(LoginPreference.PREF_LOGIN_STATUS)){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
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

        binding.apply {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()
            if(email.trim() == ""){
                edtEmail.error = "Silahkan isi Email"
                edtEmail.requestFocus()
            } else if (password.trim() == ""){
                edtPassword.error  = "Silahkan isi Password"
                edtPassword.requestFocus()
            } else {
                loginValidation(email, password)
            }
        }
    }

    private fun loginValidation(email: String, password: String) {
        mDatabasePreference.child(email.replace(".", "|")).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if(user?.email == null){
                    Toast.makeText(this@SignInActivity, "Email belum terdaftar", Toast.LENGTH_SHORT).show()
                } else {
                    if (user.password.equals(password)){
                        preferences.put(LoginPreference.PREF_LOGIN_STATUS, true)
                        preferences.put(LoginPreference.PREF_NAME, user.name.toString())
                        startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@SignInActivity, "Password salah", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignInActivity, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}