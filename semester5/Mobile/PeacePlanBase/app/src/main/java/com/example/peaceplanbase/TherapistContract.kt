package com.example.peaceplanbase


import android.provider.BaseColumns

object TherapistContract {
    const val DB_NAME="PeacePlanBase"
    const val DB_VERSION=1


    object TherapistEntry: BaseColumns {
        const val DB_TABLE = "Therapists"
        const val COLUMN_ID = "Id"
        const val COLUMN_NAME = "Name"
        const val COLUMN_AGE = "Age"
        const val COLUMN_SPECIALIZATION = "Specialization"
        const val COLUMN_EXPERIENCE = "Experience"
        const val COLUMN_DESCRIPTION = "Description"
    }
}