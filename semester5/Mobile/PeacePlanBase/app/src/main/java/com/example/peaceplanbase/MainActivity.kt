package com.example.peaceplanbase



import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.peaceplanbase.databinding.ActivityMainBinding


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var therapistViewModel: TherapistViewModel
    private lateinit var therapistAdapter: TherapistAdapter

    private val addActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val bundle = data.getBundleExtra("therapistBundle")
                    val therapist = bundle?.getParcelable<Therapist>("therapist")
                    if (therapist != null) {
                        therapistViewModel.insert(therapist)
                    }
                    Toast.makeText(this, "Therapist Added Successfully!", Toast.LENGTH_LONG).show()

                }
            } else {
                Toast.makeText(applicationContext, "Addition was canceled!", Toast.LENGTH_LONG).show()

            }
        }

    private val updateActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    val bundle = data.getBundleExtra("newTherapistBundle")
                    val therapist = bundle?.getParcelable<Therapist>("newTherapist")
                    if (therapist != null) {
                        therapistViewModel.update(therapist)
                    }
                    Toast.makeText(this, "Therapist Updated Successfully!", Toast.LENGTH_LONG).show()

                }
            } else {
                Toast.makeText(applicationContext, "Update was canceled!", Toast.LENGTH_LONG).show()

            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.therapistRecyclerView.layoutManager = LinearLayoutManager(this)

        therapistViewModel = ViewModelProvider(this).get(TherapistViewModel::class.java)
        therapistAdapter = TherapistAdapter(this, therapistViewModel, updateActivityResultLauncher)
        binding.therapistRecyclerView.adapter = therapistAdapter

        therapistViewModel.allTherapists.observe(this) { therapists ->
            if (therapists.isEmpty()) {
                Toast.makeText(applicationContext, "Empty therapist list", Toast.LENGTH_LONG).show()

            }

            therapists?.let { therapistAdapter.setTherapists(it) }
        }

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddTherapistActivity::class.java)
            addActivityResultLauncher.launch(intent)
        }
    }
}
