package com.example.matrisampleapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel


class Profiledetailsscreen : AppCompatActivity() {

    lateinit var username: TextView
    lateinit var otherdetails: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profiledetailsscreen)

        username = findViewById(R.id.username)
        otherdetails = findViewById(R.id.otherdetails)


        val imageurl = intent.getStringExtra("image")
        val usernametxt = intent.getStringExtra("username")
        val age = intent.getStringExtra("age")
        val height = intent.getStringExtra("height")
        val caste = intent.getStringExtra("caste")
        val profession = intent.getStringExtra("profession")
        val city = intent.getStringExtra("city")
        val state = intent.getStringExtra("state")
        val country = intent.getStringExtra("country")


        username.text = usernametxt


        val imageList = ArrayList<SlideModel>()



        imageList.add(SlideModel(imageurl.toString(), usernametxt.toString()))
        imageList.add(
            SlideModel(
                "https://fastly.picsum.photos/id/16/2500/1667.jpg?hmac=uAkZwYc5phCRNFTrV_prJ_0rP0EdwJaZ4ctje2bY7aE",
                usernametxt.toString()
            )
        )
        imageList.add(
            SlideModel(
                "https://fastly.picsum.photos/id/28/4928/3264.jpg?hmac=GnYF-RnBUg44PFfU5pcw_Qs0ReOyStdnZ8MtQWJqTfA",
                usernametxt.toString()
            )
        )
        imageList.add(
            SlideModel(
                "https://fastly.picsum.photos/id/16/2500/1667.jpg?hmac=uAkZwYc5phCRNFTrV_prJ_0rP0EdwJaZ4ctje2bY7aE",
                usernametxt.toString()
            )
        )

        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        imageSlider.setImageList(imageList, ScaleTypes.FIT)


        otherdetails.text =
            "${age ?: ""}, ${height ?: ""}, ${caste ?: ""}, ${profession ?: ""}, ${city ?: ""}, ${state ?: ""}, ${country ?: ""}"


    }
}
