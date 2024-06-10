package com.example.peaceplanbase

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView


class TherapistAdapter(
    private var context: Context,
    private var therapistViewModel: TherapistViewModel,
    private var updateLauncher: ActivityResultLauncher<Intent>
) : RecyclerView.Adapter<TherapistAdapter.TherapistViewHolder>() {

    private var therapists = emptyList<Therapist>() // Cached copy of therapists

    class TherapistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.therapistNameText)
        val ageTextView: TextView = itemView.findViewById(R.id.therapistAgeText)
        val specializationTextView: TextView = itemView.findViewById(R.id.therapistSpecializationText)
        val experienceTextView: TextView = itemView.findViewById(R.id.therapistExperienceText)
        val descriptionTextView: TextView = itemView.findViewById(R.id.therapistDescriptionText)

        val updateButton: ImageView = itemView.findViewById(R.id.editIcon)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TherapistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.therapist, parent, false)
        return TherapistViewHolder(view)
    }

    override fun getItemCount(): Int {
        return therapists.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: TherapistViewHolder, position: Int) {
        val therapist = therapists[position]
        holder.nameTextView.text = therapist.name
        holder.ageTextView.text = therapist.age.toString()+" years"
        holder.specializationTextView.text = therapist.specialization
        holder.experienceTextView.text = therapist.experience.toString() + " years"
        holder.descriptionTextView.text = therapist.description

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateTherapistActivity::class.java).apply {
                val bundle = Bundle()
                bundle.putParcelable("therapist", therapist)
                putExtra("therapistBundle", bundle)
            }
            updateLauncher.launch(intent)
        }

        holder.deleteButton.setOnClickListener {

            val builder = AlertDialog.Builder(context)

            builder.setTitle("Delete therapist").setMessage("Are you sure you want to delete this therapist?")
            builder.setPositiveButton("Yes") { dialog, which ->
                therapistViewModel.delete(therapist)
                Toast.makeText(context, "Therapist deleted successfully!", Toast.LENGTH_SHORT).show()

            }
            builder.setNegativeButton("No") { dialog, which ->
                Toast.makeText(context, "Deletion canceled", Toast.LENGTH_SHORT).show()

            }
            builder.show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    internal fun setTherapists(therapists: List<Therapist>) {
        this.therapists = therapists as ArrayList<Therapist>
        notifyDataSetChanged()
    }
}
