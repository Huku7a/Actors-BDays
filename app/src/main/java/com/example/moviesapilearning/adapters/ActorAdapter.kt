package com.example.moviesapilearning.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapilearning.R
import com.example.moviesapilearning.api.Bio

class ActorAdapter(private val actors : ArrayList<Bio>) : RecyclerView.Adapter<ActorAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val actorName : TextView = itemView.findViewById(R.id.actorName)
        val actorBirthDate : TextView = itemView.findViewById(R.id.actorBirthDate)
        val actorBirthPlace : TextView = itemView.findViewById(R.id.actorBirthPlace)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =  LayoutInflater.from(parent.context)
            .inflate(R.layout.actor_pod,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return actors.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = actors[position]

        holder.actorName.text = item.name
        holder.actorBirthDate.text = item.birthDate
        holder.actorBirthPlace.text = item.birthPlace
    }
}