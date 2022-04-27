package com.example.BookBath.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun addUser(user: User)

    @Query("SELECT * FROM tb_user")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT * FROM tb_user WHERE user_username LIKE :username AND user_password LIKE :password")
    fun loginMethod(username: String, password: String)
}