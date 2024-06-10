package com.example.peaceplanbase


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "therapist_table")
data class Therapist(
    val name: String,
    val age: Int,
    val specialization: String,
    val experience: Int,
    val description: String,
    @PrimaryKey val id: Int? = null
) : Parcelable

