package com.example.realtimedatabasekotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimedatabasekotlin.databinding.ActivityConsultationMainBinding

class ConsultationMain : AppCompatActivity() {
    private lateinit var binding: ActivityConsultationMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConsultationMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.addBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.editBtn.setOnClickListener {
            val intent = Intent(this, UpdateData::class.java)
            startActivity(intent)
        }
        binding.viewBtn.setOnClickListener {
            val intent = Intent(this, ViewAll::class.java)
            startActivity(intent)
        }
        binding.deleteBtn.setOnClickListener {
            val intent = Intent(this, DeleteData::class.java)
            startActivity(intent)
        }
    }


}