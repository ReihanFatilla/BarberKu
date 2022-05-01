package com.example.BookBath.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.BookBath.data.firebase.User

//class BookRepository(application: Application): AndroidViewModel(application) {
//
//
//    private val dao: BarberDao = BarberDB.invoke(application).barberDao
//
//    suspend fun getBookData(): LiveData<List<User>>? {
//        return dao.getAllUser()
//    }
//}