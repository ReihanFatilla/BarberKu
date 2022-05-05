package com.example.BarberKu.data.local.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tb_booked_barber")
@Parcelize
data class BookedBarber(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val bookerName: String,
    val barberName: String,
    val cutType: String,
    val location: String,
    val note: String,
    val time: String,
    val date: String
): Parcelable
