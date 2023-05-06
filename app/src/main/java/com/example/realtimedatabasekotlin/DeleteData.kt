package com.example.realtimedatabasekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimedatabasekotlin.databinding.ActivityDeleteDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteData : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchBtn.setOnClickListener {
            val intent= Intent(this,ReadData::class.java)
            startActivity(intent)
        }
        binding.cancelBtn.setOnClickListener {
            binding.etnumber.text.clear()
            binding.name.text=null
            binding.etnumber.text=null
            binding.etDate.text=null
            binding.address.text=null
            binding.age.text=null
        }

        binding.updateBtn.setOnClickListener {
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
        binding.readdataBtn.setOnClickListener {
            val typenumber: String = binding.etnumber.text.toString()


            if (typenumber.isNotEmpty()) {
                readData(typenumber)
            } else {
                Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show()
            }

        }
        binding.deleteBtn.setOnClickListener {
            val typenumber: String = binding.etnumber.text.toString()
            if (typenumber.isNotEmpty()) {
                database = FirebaseDatabase.getInstance().getReference("Consultants")
                database.child(typenumber).get().addOnSuccessListener {

                    if (it.exists()) {
                        database.child(typenumber).removeValue().addOnSuccessListener {
                            binding.etnumber.text.clear()
                            Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_SHORT).show()
                            binding.name.text=null
                            binding.etnumber.text=null
                            binding.etDate.text=null
                            binding.address.text=null
                            binding.age.text=null
                        }.addOnFailureListener {
                            Toast.makeText(this, "Errors in deleting the data.", Toast.LENGTH_SHORT)
                                .show()
                        }

                    } else {
                        Toast.makeText(this, "Please Enter valid Mobile Number", Toast.LENGTH_SHORT)
                            .show()

                    }
                }.addOnFailureListener {
                    Toast.makeText(this, "Errors in deleting the data.", Toast.LENGTH_SHORT).show()

                }
            } else {
                Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show()

            }
        }
    }
    private fun readData(userNumber: String) {

        database = FirebaseDatabase.getInstance().getReference("Consultants")
        database.child(userNumber).get().addOnSuccessListener {

            if (it.exists()) {

                val name = it.child("name").value
                val age = it.child("age").value
                val address = it.child("address").value
                val date = it.child("date").value
                binding.name.text = name.toString().trim();
                binding.age.text = age.toString()
                binding.address.text = address.toString()
                binding.etDate.text = date.toString()

            } else {

                Toast.makeText(this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {

            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()


        }


    }
}