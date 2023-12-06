package com.example.peaceplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.peaceplan.databinding.ActivityEditTherapistBinding

class EditTherapistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditTherapistBinding
    private lateinit var dbHelper: TherapistsDatabaseHelper
    private var therapistId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditTherapistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = TherapistsDatabaseHelper(this)

        therapistId = intent.getIntExtra("id", -1)

            val therapist=dbHelper.getTherapistById(therapistId)

            binding.updateEditTextName.setText(therapist.name)
            binding.updateEditTextAge.setText(therapist.age.toString())
            binding.updateEditTextSpecialization.setText(therapist.specialization)
            binding.updateEditTextExperience.setText(therapist.experience.toString())
            binding.updateEditTextDescription.setText(therapist.description)

            binding.updateSaveButton.setOnClickListener{
                val name = binding.updateEditTextName.text.toString()
                val age = binding.updateEditTextAge.text.toString().toIntOrNull() ?: 0
                val specialization = binding.updateEditTextSpecialization.text.toString()
                val experience = binding.updateEditTextExperience.text.toString().toIntOrNull() ?: 0
                val description = binding.updateEditTextDescription.text.toString()


                val updatedTherapist = Therapist(therapistId, name, age, specialization, experience, description)
                dbHelper.updateTherapist(updatedTherapist)
                finish()
                Toast.makeText(this, "Therapist updated successfully", Toast.LENGTH_SHORT).show()}


        binding.backButton.setOnClickListener {
            onBackPressed()
        }
    }



    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }
}