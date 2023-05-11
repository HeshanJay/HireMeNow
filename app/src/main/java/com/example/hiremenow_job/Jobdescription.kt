package com.example.hiremenow_job

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Jobdescription : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobdescription)

        val myBtn1 = findViewById<Button>(R.id.apply_btn1)
        myBtn1.setOnClickListener {
            val intent = Intent(this, Applyjobform::class.java)
            startActivity(intent)
        }
    }
}