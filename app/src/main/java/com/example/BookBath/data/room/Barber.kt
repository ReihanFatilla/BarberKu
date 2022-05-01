package com.example.BookBath.data.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_user")
data class Barber(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "user_username")
    val username: String,
    val email: String,
    @ColumnInfo(name = "user_password")
    val password: String
): Parcelable
