package com.example.readers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.readers.models.Rooms
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {



    lateinit var imagev: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imagev = findViewById(R.id.imagevId)


        Glide.with(this)
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Book-icon-bible.png/900px-Book-icon-bible.png?20201013125426")
            .into(imagev)

        imagev.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }


    }
}