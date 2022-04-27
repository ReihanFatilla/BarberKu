package com.example.BookBath.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDB : RoomDatabase() {
    abstract val userDao: UserDao

    companion object{
        @Volatile
        var instace: UserDB? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instace ?: synchronized(LOCK) {
            instace ?: buildDataBase(context).also {
                instace = it
            }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context, UserDB::class.java, "notto.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}