package com.example.peaceplan

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TherapistsDatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_NAME = "peaceplan.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "therapists"
        private const val COLUMN_ID = "therapist_id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_AGE = "age"
        private const val COLUMN_SPECIALIZATION = "specialization"
        private const val COLUMN_EXPERIENCE = "experience"
        private const val COLUMN_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_NAME TEXT," +
                    "$COLUMN_AGE INTEGER," +
                    "$COLUMN_SPECIALIZATION TEXT," +
                    "$COLUMN_EXPERIENCE INTEGER," +
                    "$COLUMN_DESCRIPTION TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       val dropTableQuery="DROP TABLE IF EXISTS $TABLE_NAME "
        db?.execSQL(dropTableQuery)
        onCreate(db)

    }
    fun insertTherapist(therapist: Therapist){

        val values = ContentValues().apply {
            put(COLUMN_NAME, therapist.name)
            put(COLUMN_AGE, therapist.age)
            put(COLUMN_SPECIALIZATION, therapist.specialization)
            put(COLUMN_EXPERIENCE, therapist.experience)
            put(COLUMN_DESCRIPTION, therapist.description)
        }

        val db = writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()

    }
    @SuppressLint("Range")
    fun getAllTherapists(): List<Therapist> {
        val therapistsList = mutableListOf<Therapist>()
        val db = readableDatabase
        val query= "SELECT * from $TABLE_NAME"
        val cursor=db.rawQuery(query,null)


        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
            val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
            val age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE))
            val specialization = cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALIZATION))
            val experience = cursor.getInt(cursor.getColumnIndex(COLUMN_EXPERIENCE))
            val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))

            val therapist = Therapist(id, name, age, specialization, experience, description)
            therapistsList.add(therapist)
        }

        cursor.close()
        db.close()

        return therapistsList
    }
    fun updateTherapist(therapist: Therapist) {
        val values = ContentValues().apply {
            put(COLUMN_NAME, therapist.name)
            put(COLUMN_AGE, therapist.age)
            put(COLUMN_SPECIALIZATION, therapist.specialization)
            put(COLUMN_EXPERIENCE, therapist.experience)
            put(COLUMN_DESCRIPTION, therapist.description)
        }

        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(therapist.id.toString())

        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }
    @SuppressLint("Range")
    fun getTherapistById(therapistId:Int): Therapist {

        val db = readableDatabase
        val query= "SELECT * from $TABLE_NAME WHERE $COLUMN_ID = $therapistId"
        val cursor=db.rawQuery(query,null)
        cursor.moveToFirst()
        val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
        val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
        val age = cursor.getInt(cursor.getColumnIndex(COLUMN_AGE))
        val specialization = cursor.getString(cursor.getColumnIndex(COLUMN_SPECIALIZATION))
        val experience = cursor.getInt(cursor.getColumnIndex(COLUMN_EXPERIENCE))
        val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))



        cursor.close()
        db.close()
        val therapist = Therapist(id, name, age, specialization, experience, description)

        return therapist
    }
    fun deleteTherapist(therapistId: Int) {
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(therapistId.toString())

        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }

}