package com.example.peaceplanbase


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
//Data access object
interface TherapistDao {

    @Query("SELECT * FROM therapist_table")
    fun getAllTherapists(): LiveData<List<Therapist>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(therapist: Therapist)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(therapist: Therapist)

    @Query("DELETE FROM therapist_table")
    fun deleteAll()

    @Query("SELECT * FROM therapist_table WHERE id = :id")
    fun getTherapistById(id: Int): Therapist?

    @Delete
    fun deleteTherapist(therapist: Therapist)
}