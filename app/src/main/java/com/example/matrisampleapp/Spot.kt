package com.example.matrisampleapp

data class Spot(
    val id: Long = counter++,
    var username:String="",
    var imageUrl: String? = null,
    var age: String ="",
    var height: String ="",
    var profession: String ="",
    var caste: String ="",
    var city:String="",
    var state:String="",
    var country:String="",
    var mobileno: String =""



) {
    companion object {
        private var counter = 0L
    }
}