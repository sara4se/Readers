package com.example.readers.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.readers.databinding.ActivityLoginBinding
import com.example.readers.data.models.User
import com.example.readers.ui.home.view.HomePage
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {


    lateinit var binding: ActivityLoginBinding

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {

        Glide.with(this@login)
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Book-icon-bible.png/900px-Book-icon-bible.png?20201013125426")
            .into(imagevId1)

        val intent = Intent(this@login, HomePage::class.java)


        btlogin.setOnClickListener {
            db.collection("Users")
                .add(User(etUsername.text.toString(), etPassword.text.toString().toInt()))
                .addOnSuccessListener {
                    Log.d("login", "Id: ${it.id}")
                }
                .addOnFailureListener {
                    Toast.makeText(this@login, "Error", Toast.LENGTH_SHORT).show()
                }


            db.collection("Users")
                .document("UserData")
                .set(User("Mohammedddddddddd", 20))
                .addOnSuccessListener {
                    Log.d("MainActivity", "Success")
                }
                .addOnFailureListener {
                    Toast.makeText(this@login, "Error", Toast.LENGTH_SHORT).show()
                }



            val bundle = Bundle()
            bundle.putString("USERNAME", etUsername.text.toString())
            bundle.putString("PASSWORD", etPassword.text.toString())
            intent.putExtras(bundle)
            startActivity(intent)
           }
        }
    }
}
