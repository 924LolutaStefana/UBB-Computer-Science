package com.example.peaceplanbase



import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peaceplanbase.databinding.ActivityUpdateTherapistBinding

@Suppress("DEPRECATION")
class UpdateTherapistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateTherapistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTherapistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra("therapistBundle")
        val therapist = bundle?.getParcelable<Therapist>("therapist")

        if (therapist != null) {
            binding.editTherapistNameText.setText(therapist.name)
            binding.editAgeTextNumber.setText(therapist.age.toString())
            binding.editSpecializationText.setText(therapist.specialization)
            binding.editExperienceTextNumber.setText(therapist.experience.toString())
            binding.editDescriptionText.setText(therapist.description)
        } else {
            println("THERAPIST IS NULL")
            finish()
        }

        binding.saveEditButton.setOnClickListener {
            val values = ContentValues()

            val newName = binding.editTherapistNameText.text.toString()
            values.put(TherapistContract.TherapistEntry.COLUMN_NAME, newName)
            val newAge = binding.editAgeTextNumber.text.toString().toInt()
            values.put(TherapistContract.TherapistEntry.COLUMN_AGE, newAge)
            val newSpecialization = binding.editSpecializationText.text.toString()
            values.put(TherapistContract.TherapistEntry.COLUMN_SPECIALIZATION, newSpecialization)
            val newExperience = binding.editExperienceTextNumber.text.toString().toInt()
            values.put(TherapistContract.TherapistEntry.COLUMN_EXPERIENCE, newExperience)
            val newDescription = binding.editDescriptionText.text.toString()
            values.put(TherapistContract.TherapistEntry.COLUMN_DESCRIPTION, newDescription)

            val newTherapist = therapist?.let { it1 ->
                Therapist(
                    id = it1.id,
                    name = newName,
                    age = newAge,
                    specialization = newSpecialization,
                    experience = newExperience,
                    description = newDescription
                )
            }

            val intent = Intent()
            val bundle = Bundle()

            bundle.putParcelable("newTherapist", newTherapist)
            intent.putExtra("newTherapistBundle", bundle)
            setResult(Activity.RESULT_OK, intent)

            finish()
        }

        binding.cancelEditButton.setOnClickListener { finish() }
    }
}
