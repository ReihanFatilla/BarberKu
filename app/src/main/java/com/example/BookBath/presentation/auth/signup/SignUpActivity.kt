package com.example.BookBath.presentation.auth.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import com.example.BookBath.data.remote.firebase.User
import com.example.BookBath.databinding.ActivitySignUpBinding
import com.example.BookBath.presentation.auth.signin.SignInActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.reift.weatherapp.helper.HelperFunction

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding


    private lateinit var mDataseReference: DatabaseReference
    private lateinit var mFirebaseInstance: FirebaseDatabase
    private lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this);
        HelperFunction.transparentStatusbar(this)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDataseReference = mFirebaseInstance.getReference("User")


        passwordShowHide()
        iniView()
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
            btnShowHideConfirm.setOnClickListener {
                if(edtPasswordConfirm.transformationMethod == PasswordTransformationMethod.getInstance()){
                    edtPasswordConfirm.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else {
                    edtPasswordConfirm.transformationMethod = PasswordTransformationMethod.getInstance()
                }
            }
        }

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
            val name = edtName.text.toString()
            val email = edtEmail.text.toString().lowercase()
            val password = edtPassword.text.toString()
            val confirmPass = edtPasswordConfirm.text.toString()

            if(name.trim() == ""){
                edtName.error = "Silahkan masukkan Nama"
                edtName.requestFocus()
            } else if(email.trim() == ""){
                edtEmail.error = "Silahkan masukkan Email"
                edtEmail.requestFocus()
            } else if(password.trim() == "") {
                edtPassword.error = "Silahkan masukkan Password"
                edtPassword.requestFocus()
            } else if(confirmPass != password) {
                edtPasswordConfirm.error = "Konfirmasi Password harus sesuai dengan Password"
                edtPasswordConfirm.requestFocus()
            } else {
                val userRegister = User(
                    name,
                    email,
                    password
                )
                saveUserAccount(email, userRegister)
                }
            }
        }

    private fun saveUserAccount(email: String, userRegister: User) {
        mDataseReference.child("email").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var user = snapshot.getValue(User::class.java)
                if(user?.email?.replace(".", "|")  == null){
                    mDataseReference.child(email.replace(".", "|")).setValue(userRegister)
                    startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, "Email Sudah di Gunakan", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@SignUpActivity, error.message, Toast.LENGTH_LONG).show()
                Log.i("registerError", error.message)
            }

        })
    }
}