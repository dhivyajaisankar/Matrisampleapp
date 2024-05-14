package com.example.matrisampleapp

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class GuestureScreenAdapter (private var dataList: List<User>, private val context:GestureScreen,  private val listener: GuestureOnITemclicklistener) :
    RecyclerView.Adapter<GuestureScreenAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestureScreenAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.guesturescreenreclayout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GuestureScreenAdapter.ViewHolder, position: Int) {
        val dataModel = dataList.get(position)

        Picasso.get()
            .load(dataModel.imageUrl)

            .into(holder.userimage);

        holder.username.text = dataModel.username
        holder.otherdetails.text= dataModel.age+ ", "+dataModel.height+ ", "+dataModel.caste+", "+dataModel.profession+", "+dataModel.city+", "+dataModel.state+", "+dataModel.country

        holder.userimage.setOnClickListener { v ->
            val intent = Intent(v.context, GestureScreen::class.java)
            v.context.startActivity(intent)

        }
        holder.username.setOnClickListener { v ->
            val intent = Intent(v.context, GestureScreen::class.java)
            v.context.startActivity(intent)

        }
        holder.otherdetails.setOnClickListener { v ->
            val intent = Intent(v.context, GestureScreen::class.java)
            v.context.startActivity(intent)

        }


        holder.yesbutton.setOnClickListener { v ->
            Log.e("*Adapter position :", holder.adapterPosition.toString());
            listener.onItemClick(holder.adapterPosition)
        }
        holder.nobutton.setOnClickListener { v ->
            listener.onItemClick(holder.adapterPosition)
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