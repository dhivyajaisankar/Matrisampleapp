package com.example.matrisampleapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserDatabase"
        private val TABLE_CONTACTS = "UserTable"
        private val id = "id"
        private val username="username"
        private val imageUrl="imageUrl"
        private val  age="age"
        private val  height="height"
        private val  profession ="profession"
        private val  caste ="caste"
        private val  city="city"
        private val  state="state"
        private val  country="country"
        private val  mobileno="mobileno"
    }
    override fun onCreate(db: SQLiteDatabase?) {

        val CREATE_CONTACTS_TABLE = "CREATE TABLE $TABLE_CONTACTS " +
                    "($id INTEGER," +
                    "$username TEXT," +
                    "$imageUrl TEXT," +
                    "$age TEXT," +
                    "$height TEXT," +
                    "$profession TEXT," +
                    "$caste TEXT," +
                    "$city TEXT," +
                    "$state TEXT," +
                    "$country TEXT," +
                    "$mobileno TEXT)"

//        //creating table with fields
//        val CREATE_CONTACTS_TABLE = ("CREATE TABLE "+TABLE_CONTACTS +"("
//                + id +"INTEGER PRIMARY KEY,"
//                + username+"TEXT,"
//                + imageUrl+"TEXT,"
//                + age +"TEXT,"
//                + height+"TEXT,"
//                + profession+"TEXT,"
//                + caste+"TEXT,"
//                + city+"TEXT,"
//                + state+"TEXT,"
//                + country+"TEXT,"
//                + mobileno+"TEXT"
//                + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


//    fun adduser(user: User):Long{
//        val db = this.writableDatabase
//        val contentValues = ContentValues()
//        contentValues.put(id, user.id)
//        contentValues.put(username, user.username)
//        contentValues.put(imageUrl, user.imageUrl)
//        contentValues.put(age, user.age)
//        contentValues.put(height, user.height)
//        contentValues.put(profession, user.profession)
//        contentValues.put(caste, user.caste)
//        contentValues.put(city, user.city)
//        contentValues.put(state, user.state)
//        contentValues.put(country, user.country)
//        contentValues.put(mobileno, user.mobileno)
//
//        // Inserting Row
//        val success = db.insert(TABLE_CONTACTS, null, contentValues)
//        //2nd argument is String containing nullColumnHack
//        db.close() // Closing database connection
//        return success
//    }


    fun addUniqueUser(user: User): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(id, user.id)
        contentValues.put(username, user.username)
        contentValues.put(imageUrl, user.imageUrl)
        contentValues.put(age, user.age)
        contentValues.put(height, user.height)
        contentValues.put(profession, user.profession)
        contentValues.put(caste, user.caste)
        contentValues.put(city, user.city)
        contentValues.put(state, user.state)
        contentValues.put(country, user.country)
        contentValues.put(mobileno, user.mobileno)

        val existingUser = getUserById(user.id) // Check if the user already exists

        if (existingUser == null)
        {
            // User does not exist, so insert it
            val success = db.insert(TABLE_CONTACTS, null, contentValues)
//            db.close()
            return success
        } else {
            // User already exists, return -1 to indicate failure
//            db.close()
            return -1
        }
    }

    // Add a function to get user by id
    @SuppressLint("Range")
    fun getUserById(userId: Long): User? {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_CONTACTS WHERE $id = ?", arrayOf(userId.toString()))
        var user: User? = null

        if (cursor.moveToFirst()) {
            user = User(
                id = cursor.getLong(cursor.getColumnIndex("id")),
                username = cursor.getString(cursor.getColumnIndex("username")),
                imageUrl = cursor.getString(cursor.getColumnIndex("imageUrl")),
                age = cursor.getString(cursor.getColumnIndex("age")),
                height = cursor.getString(cursor.getColumnIndex("height")),
                profession = cursor.getString(cursor.getColumnIndex("profession")),
                caste = cursor.getString(cursor.getColumnIndex("caste")),
                city = cursor.getString(cursor.getColumnIndex("city")),
                state = cursor.getString(cursor.getColumnIndex("state")),
                country = cursor.getString(cursor.getColumnIndex("country")),
                mobileno = cursor.getString(cursor.getColumnIndex("mobileno"))
            )
        }

        cursor.close()
//        db.close()
        return user
    }

    //method to read data
    @SuppressLint("Range")
    fun viewuser():List<User>{
        val userList:ArrayList<User> = ArrayList<User>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id: Long
        var username:String
        var imageUrl: String
        var age: String
        var height: String
        var profession: String
        var caste: String
        var city:String
        var state:String
        var country:String
        var mobileno: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getLong(cursor.getColumnIndex("id"))
                username = cursor.getString(cursor.getColumnIndex("username"))
                imageUrl = cursor.getString(cursor.getColumnIndex("imageUrl"))
                age = cursor.getString(cursor.getColumnIndex("age"))
                height = cursor.getString(cursor.getColumnIndex("height"))
                profession = cursor.getString(cursor.getColumnIndex("profession"))
                caste = cursor.getString(cursor.getColumnIndex("caste"))
                city = cursor.getString(cursor.getColumnIndex("city"))
                state = cursor.getString(cursor.getColumnIndex("state"))
                country = cursor.getString(cursor.getColumnIndex("country"))
                mobileno = cursor.getString(cursor.getColumnIndex("mobileno"))

                val user= User(id = id, username = username,
                    imageUrl= imageUrl,
                 age= age,
                 height= height,
                 profession= profession,
                 caste= caste,
                 city= city,
                 state= state,
                 country= country,
                 mobileno= mobileno
                )
                userList.add(user)
            } while (cursor.moveToNext())
        }
        return userList
    }
    //method to update data
    fun updateEmployee(user: User):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(id, user.id)
        contentValues.put(username, user.username)
        contentValues.put(imageUrl, user.imageUrl)
        contentValues.put(age, user.age)
        contentValues.put(height, user.height)
        contentValues.put(profession, user.profession)
        contentValues.put(caste, user.caste)
        contentValues.put(city, user.city)
        contentValues.put(state, user.state)
        contentValues.put(country, user.country)
        contentValues.put(mobileno, user.mobileno)

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues,"id="+user.id,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to delete data
    fun deleteuser(user: User): Int {
        val db = this.writableDatabase
        val success = db.delete(TABLE_CONTACTS, "$id = ?", arrayOf(user.id.toString()))
        // No need to close the database connection here
        return success
    }
}