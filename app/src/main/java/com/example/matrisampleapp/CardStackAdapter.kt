package com.example.matrisampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class CardStackAdapter (
    private var spots: List<Spot> = emptyList(),
            private val listener: GuestureOnITemclicklistener
) : RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_spot, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val spot = spots[position]
        holder.name.text = spot.username

        holder.otherdetails.text = spot.age+ ", "+spot.height+ ", "+spot.caste+", "+spot.profession+", "+spot.city+", "+spot.state+", "+spot.country

        Picasso.get()
            .load(spot.imageUrl)

            .into(holder.image)

        holder.itemView.setOnClickListener { v ->
            Toast.makeText(v.context, spot.username, Toast.LENGTH_SHORT).show()
        }
        holder.yesbutton.setOnClickListener { v ->
            listener.onItemClick(holder.adapterPosition)

        }
        holder.closebutton.setOnClickListener { v ->
            listener.onItemClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return spots.size
    }

    fun setSpots(spots: List<Spot>) {
        this.spots = spots
    }

    fun getSpots(): List<Spot> {
        return spots
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.userimage)
        var name: TextView = view.findViewById(R.id.username)
        var otherdetails: TextView = view.findViewById(R.id.otherdetails)
        var closebutton: ImageView = view.findViewById(R.id.closebutton)
        var yesbutton: ImageView = view.findViewById(R.id.yesbutton)


    }

}