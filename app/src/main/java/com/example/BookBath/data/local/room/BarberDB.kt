package com.example.BookBath.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.BookBath.data.remote.firebase.User

@Database(entities = [BookedBarber::class], version = 1)
abstract class BarberDB : RoomDatabase() {
    abstract val barberDao: BarberDao

    companion object{
        @Volatile
        var instace: BarberDB? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instace ?: synchronized(LOCK) {
            instace ?: buildDataBase(context).also {
                instace = it
            }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context, BarberDB::class.java, "notto.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}