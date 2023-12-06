package com.example.peaceplan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.peaceplan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: TherapistsDatabaseHelper
    private lateinit var therapistAdapter: TherapistsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        db= TherapistsDatabaseHelper(this)
        therapistAdapter= TherapistsAdapter(db.getAllTherapists(),this)
        binding.recyclerViewTherapists.layoutManager=LinearLayoutManager(this)
        binding.recyclerViewTherapists.adapter=therapistAdapter

        binding.addButton.setOnClickListener{
            val intent= Intent(this,AddTherapistActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        therapistAdapter.refreshData(db.getAllTherapists())
    }
}