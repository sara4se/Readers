package com.example.readers

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.readers.adapters.UsersAdapter
import com.example.readers.databinding.ActivityPeopleBinding
import com.example.readers.models.User
import com.google.firebase.firestore.FieldValue.delete
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PeopleActivity : AppCompatActivity() {


    lateinit var binding: ActivityPeopleBinding


    val db = Firebase.firestore
    val users: MutableList<User> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPeopleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // get from fireStore will be here
        binding.apply {
            btnGet.setOnClickListener {

                db.collection("Users")
                    .addSnapshotListener { value, error ->

                        if (error == null) {

                            users.clear()
                            for (document in value?.documents!!) {

                                val name = document.data?.getValue("name") as String
                                val age = document.data?.getValue("age") as Long

                                Log.d("MainActivity", "${document.id} --- $name - $age")

                                val currentUser = User(name, age.toInt())
                                currentUser.id = document.id
                                users.add(currentUser)

                            }

                            rvUsers.adapter = UsersAdapter(users)

                        } else {
                            Toast.makeText(
                                this@PeopleActivity,
                                "Error Get Data",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }


                    }

            }
            btnDelete.setOnClickListener {
                db.collection("Users")
                    .addSnapshotListener { value, error ->

                        if (error == null) {

                            users.clear()
                            for (document in value?.documents!!) {

                                val name = document.data?.getValue("name") as String
                                val age = document.data?.getValue("age") as Long

                                Log.d("MainActivity", "${document.id} --- $name - $age")

                                val currentUser = User(name, age.toInt())
                                currentUser.id = document.id
                                users.remove(currentUser)
                                db.collection("Users").document(currentUser.id).delete()
                                    .addOnSuccessListener {
                                        Log.d(
                                            "",
                                            "DocumentSnapshot successfully deleted!"

                                        )
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(
                                            "",
                                            "Error deleting document",
                                            e
                                        )
                                    }

                            }


                            rvUsers.adapter = UsersAdapter(users)

                        } else {
                            Toast.makeText(
                                this@PeopleActivity,
                                "Error Get Data",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
            }
        }
    }
}

//
//
////                    .addSnapshotListener { value, error ->
////                        if (error == null) {
////                            users.clear()
////
//                for (user in users.iterator()) {
//                    db.collection("Users").document("UserData")
//                        .delete()
//                        .addOnSuccessListener {
//                            Log.d(
//                                "",
//                                "DocumentSnapshot successfully deleted!"
//
//                            )
//                        }
//                        .addOnFailureListener { e ->
//                            Log.w(
//                                "",
//                                "Error deleting document",
//                                e
//                            )
//                        }
//                }
//
//
//            }
//                db.collection("Users")
//                    .addSnapshotListener { value, error ->
//
//
//                            for (document in value?.documents!!) {
//                                val name = document.data?.getValue("name") as String
//                                val age = document.data?.getValue("age") as Long
//                                val currentUser = User(name, age.toInt())
//                                currentUser.id = document.id
//                                if (currentUser.id.equals(document.id)) {
//                                    // document.data?.remove("name")
//                                    users.remove(currentUser)
//                                  }
//                            }
//                            rvUsers.adapter = UsersAdapter(users)
//                        }
//                    }
// after that we are getting the document
// which we have to delete.
// after passing the document id we are calling
// delete method to delete this document.
