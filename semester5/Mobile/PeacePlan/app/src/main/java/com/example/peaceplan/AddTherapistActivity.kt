package com.example.peaceplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.peaceplan.databinding.ActivityAddTherapistBinding

class AddTherapistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTherapistBinding
    private lateinit var dbHelper: TherapistsDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTherapistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = TherapistsDatabaseHelper(this)

        binding.saveButton.setOnClickListener {
            addTherapist()
        }
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

    }
    private fun addTherapist() {
        val name = binding.editTextName.text.toString()
        val age = binding.editTextAge.text.toString().toIntOrNull() ?: 0
        val specialization = binding.editTextSpecialization.text.toString()
        val experience = binding.editTextExperience.text.toString().toIntOrNull() ?: 0
        val description = binding.editTextDescription.text.toString()

        if (name.isBlank() || specialization.isBlank() || description.isBlank()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val therapist = Therapist(0,name, age, specialization, experience, description)

        dbHelper.insertTherapist(therapist)
        finish()
        Toast.makeText(this, "Therapist added successfully", Toast.LENGTH_SHORT).show()


    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}