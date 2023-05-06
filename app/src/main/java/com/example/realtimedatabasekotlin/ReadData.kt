package com.example.realtimedatabasekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimedatabasekotlin.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadData : AppCompatActivity() {

    private lateinit var binding: ActivityReadDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updatedataBtn.setOnClickListener {
            val intent= Intent(this,UpdateData::class.java)
            startActivity(intent)
        }
        binding.addBtn.setOnClickListener {
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        binding.viewBtn.setOnClickListener {
            val intent= Intent(this,ViewAll::class.java)
            startActivity(intent)
        }
        binding.homeBtn.setOnClickListener {
            val intent= Intent(this,ConsultationMain::class.java)
            startActivity(intent)
        }
        binding.searchBtn.setOnClickListener {
            val intent=Intent(this,ReadData::class.java)
            startActivity(intent)
        }


        val number = intent.getStringExtra("number")
        if (number != null) {
            readData(number)

        }

        binding.readdataBtn.setOnClickListener {
            val typenumber: String = binding.etnumber.text.toString()


            if (typenumber.isNotEmpty()) {
                readData(typenumber)
            } else {
                Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show()
            }

        }
        binding.updateBtn.setOnClickListener {
            val intent = Intent(this, UpdateData::class.java)
            startActivity(intent)
        }
        binding.btndelete.setOnClickListener {
            val typenumber: String = binding.etnumber.text.toString()
            if (typenumber.isNotEmpty()) {
                deletedata(typenumber)
            } else {
                Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show()

            }
        }


    }

    private fun deletedata(userNumber: String) {
        database = FirebaseDatabase.getInstance().getReference("Consultants")
        database.child(userNumber).removeValue().addOnSuccessListener {
            binding.etnumber.text.clear()
            binding.viewname.text = null
            binding.viewnumber.text = null
            binding.viewage.text = null
            binding.viewaddress.text = null
            binding.viewdate.text = null
            Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Errors in deleting the data.", Toast.LENGTH_SHORT).show()

        }
    }


    private fun readData(userNumber: String) {

        database = FirebaseDatabase.getInstance().getReference("Consultants")
        database.child(userNumber).get().addOnSuccessListener {

            if (it.exists()) {

                val name = it.child("name").value
                val number = it.child("number").value
                val age = it.child("age").value
                val address = it.child("address").value
                val date = it.child("date").value
                binding.viewname.text = name.toString().trim();
                binding.viewnumber.text = number.toString()
                binding.viewage.text = age.toString()
                binding.viewaddress.text = address.toString()
                binding.viewdate.text = date.toString()

            } else {

                Toast.makeText(this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {

            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()


        }


    }
}

