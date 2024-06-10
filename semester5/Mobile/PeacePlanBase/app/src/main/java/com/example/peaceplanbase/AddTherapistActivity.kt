package com.example.peaceplanbase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.peaceplanbase.databinding.ActivityAddTherapistBinding


class AddTherapistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTherapistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTherapistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveAddButton.setOnClickListener {

            if (validateInput()) {
                val name = binding.therapistNameText.text.toString()
                val age = binding.ageTextNumber.text.toString().toInt()
                val specialization = binding.therapistSpecializationText.text.toString()
                val experience = binding.therapistExperienceText.text.toString().toInt()
                val description = binding.therapistDescriptionText.text.toString()

                val therapist = Therapist(
                    name = name,
                    age = age,
                    specialization = specialization,
                    experience = experience,
                    description = description
                )

                val intent = Intent()
                val bundle = Bundle()
                bundle.putParcelable("therapist", therapist)
                intent.putExtra("therapistBundle", bundle)

                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
        binding.cancelAddButton.setOnClickListener { finish() }
    }
    private fun validateInput(): Boolean {

        if (TextUtils.isEmpty(binding.therapistNameText.text) ||
            TextUtils.isEmpty(binding.ageTextNumber.text) ||
            TextUtils.isEmpty(binding.therapistSpecializationText.text) ||
            TextUtils.isEmpty(binding.therapistExperienceText.text) ||
            TextUtils.isEmpty(binding.therapistDescriptionText.text)
        ) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }



        return true
    }
}
