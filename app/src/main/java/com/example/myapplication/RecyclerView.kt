package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import MyDataItem

class PersonAdapter(private val personList: List<MyDataItem>) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUserId: TextView = itemView.findViewById(R.id.tvUserId)
        val tvId: TextView = itemView.findViewById(R.id.tvId)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvBody: TextView = itemView.findViewById(R.id.tvBody)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val currentItem = personList[position]
        holder.tvUserId.text = "User ID: ${currentItem.userId}"
        holder.tvId.text = "ID: ${currentItem.id}"
        holder.tvTitle.text = "Title: ${currentItem.title}"
        holder.tvBody.text = "Body: ${currentItem.body}"
    }

    override fun getItemCount() = personList.size
}
