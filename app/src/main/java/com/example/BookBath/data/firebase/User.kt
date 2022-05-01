package com.example.BookBath.data.firebase

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String? = "",
    val email: String? = "",
    val password: String? = ""
): Parcelable
