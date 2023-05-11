package com.example.hiremenow_job

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myBtn1 = findViewById<Button>(R.id.moreD_btn1)
        myBtn1.setOnClickListener {
            val intent = Intent(this, Jobdescription::class.java)
            startActivity(intent)
        }
    }
}