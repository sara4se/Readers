package com.example.readers.ui.navigate.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.readers.R
import com.example.readers.ui.home.view.HomeFragment

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container1, HomeFragment.newInstance())
            .commit()
    }
}