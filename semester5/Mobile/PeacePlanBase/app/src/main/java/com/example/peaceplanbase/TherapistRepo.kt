package com.example.peaceplanbase


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData


class TherapistRepo(private val therapistDao: TherapistDao) {

    val allTherapists: LiveData<List<Therapist>> = therapistDao.getAllTherapists()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(therapist: Therapist) {
        therapistDao.insert(therapist)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getById(id: Int): Therapist? {
        return therapistDao.getTherapistById(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(therapist: Therapist) {
        return therapistDao.deleteTherapist(therapist)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(therapist: Therapist) {
        return therapistDao.update(therapist)
    }
}