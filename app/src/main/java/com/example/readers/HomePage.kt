package com.example.readers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.readers.fragment.FirstFragment
import com.example.readers.fragment.SecondFragment

class HomePage : AppCompatActivity() {
    lateinit var userNametv: TextView
    lateinit var passwordtv: TextView
    lateinit var webView: WebView
    lateinit var  fm : FrameLayout
    lateinit var  fm2 : FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        userNametv=findViewById(R.id.textView2)
        passwordtv=findViewById(R.id.textView3)
        fm=findViewById(R.id.frgmentContiner1)
        fm2=findViewById(R.id.frgmentContiner2)

        val bundle= intent.extras
        val comingUsername = bundle?.getString("USERNAME")
        val comingPassword = bundle?.getString("PASSWORD")

        userNametv.text=comingUsername
        passwordtv.text=comingPassword

         supportFragmentManager.beginTransaction()
             .replace(R.id.frgmentContiner1, FirstFragment(), "FirstFragment")
             .commit()

             fm.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.frgmentContiner2, SecondFragment(), "SecondFragment")
            .commit()
//        val comingLink = bundle?.getString("LINK") ?: ""
//        webView = findViewById(R.id.roomWebPageView)
//        webView.loadUrl(comingLink)
            fm2.setOnClickListener {
            val intent = Intent(this, PeopleActivity::class.java)
            startActivity(intent)
        }
    }
}