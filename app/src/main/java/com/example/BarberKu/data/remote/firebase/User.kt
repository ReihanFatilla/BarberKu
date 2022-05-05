package com.example.BarberKu.data.remote.firebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val name: String? = "",
    val email: String? = "",
    val password: String? = ""
): Parcelable
