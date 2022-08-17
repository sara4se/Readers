package com.example.readers.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.readers.R

class FirstFragment: Fragment(){

    lateinit var image1Fragment: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val view = inflater.inflate(R.layout.fragment_first, container, false)

        image1Fragment = view.findViewById(R.id.imageInFragmentFirst)
        Glide.with(this)
            .load("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Book-icon-bible.png/900px-Book-icon-bible.png?20201013125426")
            .into(image1Fragment)


        return view
    }

}


