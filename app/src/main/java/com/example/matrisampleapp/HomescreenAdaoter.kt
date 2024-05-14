package com.example.matrisampleapp

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.compose.material3.contentColorFor
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class HomescreenAdaoter (private var dataList: List<User>, private val context:Homescreen,  private val listener: OnItemClickListener) :
    RecyclerView.Adapter<HomescreenAdaoter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomescreenAdaoter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.homescreenreclayout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HomescreenAdaoter.ViewHolder, position: Int) {
        val dataModel = dataList.get(position)

        Picasso.get()
            .load(dataModel.imageUrl)

            .into(holder.userimage);

        holder.username.text = dataModel.username
        holder.otherdetails.text= dataModel.age+ ", "+dataModel.height+ ", "+dataModel.caste+", "+dataModel.profession+", "+dataModel.city+", "+dataModel.state+", "+dataModel.country

        holder.userimage.setOnClickListener { v ->
            val intent = Intent(v.context, Profiledetailsscreen::class.java)
            intent.putExtra("image",dataModel.imageUrl)
            intent.putExtra("username",dataModel.username)
            intent.putExtra("age",dataModel.age)
            intent.putExtra("height",dataModel.height)
            intent.putExtra("caste",dataModel.caste)
            intent.putExtra("profession",dataModel.profession)
            intent.putExtra("city",dataModel.city)
            intent.putExtra("state",dataModel.state)
            intent.putExtra("country",dataModel.country)
            v.context.startActivity(intent)

        }
        holder.username.setOnClickListener { v ->
            val intent = Intent(v.context, Profiledetailsscreen::class.java)
            v.context.startActivity(intent)

        }
        holder.otherdetails.setOnClickListener { v ->
            val intent = Intent(v.context, Profiledetailsscreen::class.java)
            v.context.startActivity(intent)

        }


        holder.yesbutton.setOnClickListener { v ->
            Log.e("*Adapter position :", holder.adapterPosition.toString());
            listener.onItemClick(dataList.get(position).id,holder.adapterPosition)
        }
        holder.nobutton.setOnClickListener { v ->
            listener.onItemClick(dataList.get(position).id,holder.adapterPosition)
        }

    }



    override fun getItemCount(): Int {
        return dataList.size
    }


    class ViewHolder(itemLayoutView: View) : RecyclerView.ViewHolder(itemLayoutView) {
        var userimage: ImageView
        var username: TextView
        var otherdetails: TextView
        var yesbutton: TextView
        var nobutton: TextView



        init {
            userimage = itemLayoutView.findViewById(R.id.userimage)
            username = itemLayoutView.findViewById(R.id.username)
            otherdetails = itemLayoutView.findViewById(R.id.otherdetails)
            yesbutton = itemLayoutView.findViewById(R.id.yesbutton)
            nobutton = itemLayoutView.findViewById(R.id.nobutton)




        }

    }

}