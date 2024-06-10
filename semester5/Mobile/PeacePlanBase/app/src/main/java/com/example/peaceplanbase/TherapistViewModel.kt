package com.example.peaceplanbase


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TherapistViewModel(application: Application): AndroidViewModel(application) {
    private val repo: TherapistRepo

    val allTherapists: LiveData<List<Therapist>>

    init {
        val therapistDao = TherapistDatabase.getDatabase(application, viewModelScope).wordDao()
        repo = TherapistRepo(therapistDao)
        allTherapists = repo.allTherapists
    }

    //initiates a coroutine
    fun insert(therapist: Therapist) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(therapist)
    }


    fun delete(therapist: Therapist) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(therapist)
    }


    fun getById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repo.getById(id)
    }


    fun update(therapist: Therapist) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(therapist)
    }
}