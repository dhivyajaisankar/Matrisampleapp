package com.example.matrisampleapp

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey

open class User(
    var id: Long = 0,
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
)

//open class User(
//    @PrimaryKey var id: Long = 0,
//    var username:String="",
//    var imageUrl: String? = null,
//    var age: String ="",
//    var height: String ="",
//    var profession: String ="",
//    var caste: String ="",
//    var city:String="",
//    var state:String="",
//    var country:String="",
//    var mobileno: String =""
//) : RealmModel


