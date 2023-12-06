package com.example.peaceplan

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TherapistsAdapter(private var therapists : List <Therapist>, context:Context):
    RecyclerView.Adapter<TherapistsAdapter.TherapistViewHolder>() {
    private val db:TherapistsDatabaseHelper= TherapistsDatabaseHelper(context)

    class TherapistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewTherapistName)
        val ageTextView: TextView = itemView.findViewById(R.id.textViewTherapistAge)
        val specializationTextView: TextView = itemView.findViewById(R.id.textViewTherapistSpecialization)
        val experienceTextView: TextView = itemView.findViewById(R.id.textViewTherapistExperience)
        val descriptionTextView: TextView = itemView.findViewById(R.id.textViewTherapistDescription)
        val updateButton: ImageView = itemView.findViewById(R.id.updateButton)
        val deleteButton: ImageView = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TherapistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.therapist_item, parent, false)
        return TherapistViewHolder(view)
    }

    override fun onBindViewHolder(holder: TherapistViewHolder, position: Int) {
        val therapist = therapists[position]

        holder.nameTextView.text = therapist.name
        holder.ageTextView.text = "Age: ${therapist.age}"
        holder.specializationTextView.text = "Specialization: ${therapist.specialization}"
        holder.experienceTextView.text = "Experience: ${therapist.experience} years"
        holder.descriptionTextView.text = "Description: ${therapist.description}"

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, EditTherapistActivity::class.java).apply{
                putExtra("id", therapist.id)

            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener{
            val alertDialogBuilder = AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setTitle("Confirm Deletion")
            alertDialogBuilder.setMessage("Are you sure you want to delete this therapist?")

            alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
                db.deleteTherapist(therapist.id)
                refreshData(db.getAllTherapists())
            }

            alertDialogBuilder.setNegativeButton("No") { _, _ ->
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return therapists.size
    }
    fun refreshData(newTherapists:List<Therapist>){
        therapists=newTherapists
        notifyDataSetChanged()
    }
}