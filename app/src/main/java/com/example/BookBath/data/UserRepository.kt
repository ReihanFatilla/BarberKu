package com.example.BookBath.data

import androidx.lifecycle.LiveData
import com.example.BookBath.data.room.User
import com.example.BookBath.data.room.UserDao

class UserRepository(private val dao: UserDao) {

    suspend fun insert(user: User) {
        dao.addUser(user)
    }

    suspend fun getAllData(): LiveData<List<User>>? {
        return dao.getAllUser()
    }
}