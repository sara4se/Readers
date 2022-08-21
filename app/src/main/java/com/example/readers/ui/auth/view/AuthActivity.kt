package com.example.readers.ui.auth.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.readers.R
import com.example.readers.ui.auth.splash.view.SplashFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SplashFragment.newInstance())
            .commit()
    }
}