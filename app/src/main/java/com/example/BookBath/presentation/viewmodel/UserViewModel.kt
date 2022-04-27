package com.example.BookBath.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.BookBath.MainActivity
import com.example.BookBath.data.room.User
import com.example.BookBath.data.room.UserDB
import com.example.BookBath.data.room.UserDao
import com.example.BookBath.databinding.ActivitySignInBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val userDao: UserDao = UserDB.invoke(application).userDao

    fun registerUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.addUser(user)
        }
    }

    fun loginUser(context: Context ,binding: ActivitySignInBinding, username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val userLogin = userDao.loginMethod(username, password)
            if(userLogin == null){
                binding.edtUsername.error = "Invalid username or password"
                binding.edtPassword.error = "Invalid username or password"
            } else {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(context, intent, null)
            }
        }
    }
}
