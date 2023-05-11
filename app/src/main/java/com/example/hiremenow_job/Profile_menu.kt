package com.example.hiremenow_job

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Profile_menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_menu)

        val myBtn2 = findViewById<Button>(R.id.appliedjob_btn)
        myBtn2.setOnClickListener {
            val intent = Intent(this, Appliedjobs::class.java)
            startActivity(intent)
        }
    }
}