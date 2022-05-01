package com.example.BookBath.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BarberDao {

    @Insert
    suspend fun addBarber(bookedBarber: BookedBarber)

    @Delete
    suspend fun deleteBarber(bookedBarber: BookedBarber)

    @Query("SELECT * FROM tb_booked_barber")
    fun getAllBarber(): LiveData<List<BookedBarber>>
}