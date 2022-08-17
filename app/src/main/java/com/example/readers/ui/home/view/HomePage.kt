package com.example.readers.ui.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.TextView
import com.example.readers.R
import com.example.readers.databinding.ActivityHomePageBinding
import com.example.readers.ui.PeopleActivity
import com.example.readers.ui.fragment.FirstFragment
import com.example.readers.ui.fragment.SecondFragment
import com.example.readers.ui.signup

class HomePage : AppCompatActivity() {
    lateinit var userNametv: TextView
    lateinit var passwordtv: TextView
    lateinit var webView: WebView
    lateinit var fm: FrameLayout
    lateinit var fm2: FrameLayout
    lateinit var viewBinding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityHomePageBinding.inflate(layoutInflater)

        setContentView(viewBinding.root)
        viewBinding.apply {
            fm = findViewById(R.id.frgmentContiner1)
            fm2 = findViewById(R.id.frgmentContiner2)

            val bundle = intent.extras
            val comingUsername = bundle?.getString("USERNAME")
            val comingPassword = bundle?.getString("PASSWORD")

            userNametv.text = comingUsername
            passwordtv.text = comingPassword

            supportFragmentManager.beginTransaction()
                .replace(R.id.frgmentContiner1, FirstFragment(), "FirstFragment")
                .commit()

            fm.setOnClickListener {
                val intent = Intent(this@HomePage, signup::class.java)
                startActivity(intent)
            }

            supportFragmentManager.beginTransaction()
                .replace(R.id.frgmentContiner2, SecondFragment(), "SecondFragment")
                .commit()
//        val comingLink = bundle?.getString("LINK") ?: ""
//        webView = findViewById(R.id.roomWebPageView)
//        webView.loadUrl(comingLink)
            fm2.setOnClickListener {
                val intent = Intent(this@HomePage, PeopleActivity::class.java)
                startActivity(intent)
            }
        }
    }
}