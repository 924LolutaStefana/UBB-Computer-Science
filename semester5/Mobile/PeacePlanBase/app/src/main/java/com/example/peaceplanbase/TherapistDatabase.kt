package com.example.peaceplanbase


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Therapist::class], version = 1, exportSchema = false)
abstract class TherapistDatabase: RoomDatabase() {
    abstract fun wordDao(): TherapistDao


    companion object {

        @Volatile
        private var INSTANCE: TherapistDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): TherapistDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TherapistDatabase::class.java,
                    "word_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

//                INSTANCE?.let { database ->
//                    scope.launch(Dispatchers.IO) {
//                        populateDatabase(database.wordDao())
//                    }
//                }
            }
        }

        fun populateDatabase(therapistDao: TherapistDao) {

            therapistDao.deleteAll()

            var therapist = Therapist(
                name = "John Doe",
                age = 35,
                specialization = "Psychologist",
                experience = 10,
                description = "Experienced Psychologist"
            )
            therapistDao.insert(therapist)

            therapist = Therapist(
                name = "Jane Smith",
                age = 40,
                specialization = "Counselor",
                experience = 8,
                description = "Certified Counselor"
            )
            therapistDao.insert(therapist)

            therapist = Therapist(
                name = "Bob Johnson",
                age = 45,
                specialization = "Therapist",
                experience = 12,
                description = "Licensed Therapist"
            )
            therapistDao.insert(therapist)

            therapist = Therapist(
                name = "Alice Williams",
                age = 38,
                specialization = "Psychiatrist",
                experience = 15,
                description = "Board-Certified Psychiatrist"
            )
            therapistDao.insert(therapist)
        }
    }
}