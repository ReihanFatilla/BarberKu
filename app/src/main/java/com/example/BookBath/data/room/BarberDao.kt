package com.example.BookBath.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BarberDao {

    @Insert
    fun addUser(user: Barber)

    @Query("SELECT * FROM tb_user")
    fun getAllUser(): LiveData<List<Barber>>
}