package com.example.realtimedatabasekotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimedatabasekotlin.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {
    val datesArray= arrayOf("Sat 3 pm - 5 pm","Sun 3 pm - 5 pm","Wed 4 pm - 6 pm")
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner=binding.spinner
        val arrayAdapter= ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,datesArray)
        spinner.adapter=arrayAdapter


        binding.viewBtn.setOnClickListener {
            val intent=Intent(this,ViewAll::class.java)
            startActivity(intent)
        }

        binding.updateBtn.setOnClickListener {
            val intent=Intent(this,UpdateData::class.java)
            startActivity(intent)
        }
        binding.searchBtn.setOnClickListener {
            val intent=Intent(this,ReadData::class.java)
            startActivity(intent)
        }
        binding.homeBtn.setOnClickListener {
            val intent=Intent(this,ConsultationMain::class.java)
            startActivity(intent)
        }

        binding.button9.setOnClickListener {
            val name = binding.name.text.trim().toString()
            val number = binding.number.text.trim().toString()
            val age = binding.age.text.trim().toString()
            val address = binding.address.text.trim().toString()
           val date=spinner.selectedItem.toString()

            database = FirebaseDatabase.getInstance().getReference("Consultants")
            val User = User(name, number, age, address, date)

            database.child(number).setValue(User).addOnSuccessListener {

                val intent = Intent(this@MainActivity, ReadData::class.java)
                intent.putExtra("number", number)
                startActivity(intent)
                Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {

                Toast.makeText(this, "Failed to save", Toast.LENGTH_SHORT).show()


            }


        }
        binding.button7.setOnClickListener {
            binding.name.text.clear()
            binding.number.text.clear()
            binding.age.text.clear()
            binding.address.text.clear()

        }

    }
}
