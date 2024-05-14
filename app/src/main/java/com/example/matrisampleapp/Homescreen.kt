package com.example.matrisampleapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Homescreen : AppCompatActivity(), OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dotsImageView: ImageView

    var dataList = ArrayList<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        GlobalScope.launch(Dispatchers.Main) {
            saveRecord()
            viewRecord()
        }



        dotsImageView = findViewById(R.id.dotsImageView)


        dotsImageView.setOnClickListener { v ->
            val intent = Intent(v.context, GestureScreen::class.java)
            v.context.startActivity(intent)

        }

    }


    fun saveRecord() {
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val userIdsSet = HashSet<Long>() // HashSet to keep track of added user IDs

        @SuppressLint("SuspiciousIndentation")
        fun addUniqueUser(user: User) {
            val userdb: List<User> = databaseHandler.viewuser()
            if (userdb.isNotEmpty()) {
                for (i in userdb) {
                    if (i.id != user.id) {
                        val status = databaseHandler.addUniqueUser(user)
                        Log.e("* Status:", "id not matched, data inserted  :" + status)
                    } else {
                        Log.e("* Status:", "id matched, data not inserted")
                    }
                }
            } else {
                val status = databaseHandler.addUniqueUser(user)
                Log.e("* Status:", "inserted success:" + status)

            }
        }

        val status1 = addUniqueUser(
            User(
                0,
                "Ria",
                "https://fastly.picsum.photos/id/19/2500/1667.jpg?hmac=7epGozH4QjToGaBf_xb2HbFTXoV5o8n_cYzB7I4lt6g",
                "23 Yrs",
                "5 ft 2 in",
                "Teacher",
                "Nair",
                "Coimbatore",
                "Tamilnadu",
                "India",
                "8843890999"
            )
        )
        val status2 = addUniqueUser(
            User(
                1,
                "Dheekshita",
                "https://fastly.picsum.photos/id/17/2500/1667.jpg?hmac=HD-JrnNUZjFiP2UZQvWcKrgLoC_pc_ouUSWv8kHsJJY",
                "30 Yrs",
                "4 ft 2 in",
                "Doctor",
                "Reddy",
                "Bangalore",
                "Karnataka",
                "India",
                "9843890459"
            )
        )
        val status3 = addUniqueUser(
            User(
                2,
                "Pavithra",
                "https://fastly.picsum.photos/id/28/4928/3264.jpg?hmac=GnYF-RnBUg44PFfU5pcw_Qs0ReOyStdnZ8MtQWJqTfA",
                "19 Yrs",
                "5 ft 9 in",
                "Developer",
                "Nair",
                "Chennai",
                "Tamilnadu",
                "India",
                "9843890779"
            )
        )
        val status4 = addUniqueUser(
            User(
                3,
                "Geetha",
                "https://fastly.picsum.photos/id/16/2500/1667.jpg?hmac=uAkZwYc5phCRNFTrV_prJ_0rP0EdwJaZ4ctje2bY7aE",
                "20 Yrs",
                "4 ft 2 in",
                "Dentist",
                "Naidu",
                "Kochi",
                "Kerala",
                "India",
                "9843890955"
            )
        )
        val status5 = addUniqueUser(
            User(
                4,
                "Vidya",
                "https://fastly.picsum.photos/id/13/2500/1667.jpg?hmac=SoX9UoHhN8HyklRA4A3vcCWJMVtiBXUg0W4ljWTor7s",
                "20 Yrs",
                "4 ft 9 in",
                "Professor",
                "Naikar",
                "Trichy",
                "Tamilnadu",
                "India",
                "9843890999"
            )
        )


    }


    fun viewRecord() {
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        val user: List<User> = databaseHandler.viewuser()
        Log.e("*List Size :", user.size.toString())
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter =
            HomescreenAdaoter(user, this, listener = this)
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onItemClick(id: Long, position: Int) {

        showCustomDialog()


        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (id.toString().isNotEmpty()) {
//                    val user: List<User> = databaseHandler.viewuser()

            val status = databaseHandler.deleteuser(User(id, "", ""))
            if (status > -1) {
                viewRecord()
            }
        } else {
            Toast.makeText(
                applicationContext,
                "id or name or email cannot be blank",
                Toast.LENGTH_LONG
            ).show()
        }


    }


    private fun showCustomDialog() {
        val dialogView = layoutInflater.inflate(R.layout.custom_layout, null)
        val builder = android.app.AlertDialog.Builder(this)
            .setView(dialogView)
        val dialog = builder.create()
        dialog.show()

        val cancel_btn = dialogView.findViewById<TextView>(R.id.cancel_btn)
        val confirm_btn = dialogView.findViewById<TextView>(R.id.confirm_btn)
        cancel_btn.setOnClickListener {

            dialog.dismiss()
        }
        confirm_btn.setOnClickListener {
            dialog.dismiss()

        }
    }

}
















