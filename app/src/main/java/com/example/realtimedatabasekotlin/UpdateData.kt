package com.example.realtimedatabasekotlin

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realtimedatabasekotlin.databinding.ActivityUpdateDataBinding
import com.google.android.gms.common.util.CollectionUtils.mapOf
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateData : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancelBtn.setOnClickListener {
            binding.etnumber.text.clear()
            binding.name.text=null
            binding.etnumber.text=null
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
        binding.searchBtn.setOnClickListener {
            val intent=Intent(this,ReadData::class.java)
            startActivity(intent)
        }

        binding.readdataBtn.setOnClickListener {
            val userNumber = binding.etnumber.text.toString()
            database = FirebaseDatabase.getInstance().getReference("Consultants")
            database.child(userNumber).get().addOnSuccessListener {

                if (it.exists()) {

                    val nameTxt = it.child("name").value
                    val ageTxt = it.child("age").value
                    val addressTxt = it.child("address").value
                    val dateTxt = it.child("date").value
//                Toast.makeText(this,"Successfuly Read",Toast.LENGTH_SHORT).show()
                    binding.name.setText(nameTxt.toString().trim())
                    binding.address.setText(addressTxt.toString())
                    binding.age.setText(ageTxt.toString())
                    when (dateTxt) {
                        "Sat 3 pm - 5 pm" -> {
                            binding.radioGroup.check(R.id.date1)
                        }
                        "Sun 3 pm - 5 pm" -> {
                            binding.radioGroup.check(R.id.date2)
                        }
                        "Wed 4 pm - 6 pm" -> {
                            binding.radioGroup.check(R.id.date3)
                        }
                    }




                } else {

                    Toast.makeText(this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show()
                }

            }.addOnFailureListener {

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()


            }

        }
        binding.updateDataBtn.setOnClickListener {

            val name = binding.name.text.toString()
            val number = binding.etnumber.text.toString()
            val address = binding.address.text.toString()
            val age = binding.age.text.toString()
            val radio: RadioButton = findViewById(binding.radioGroup.checkedRadioButtonId)
            val date = radio.text.toString()

            updateData(name, address, number, age, date)

        }

    }

    private fun updateData(
        name: String,
        address: String,
        number: String,
        age: String,
        date: String
    ) {

        database = FirebaseDatabase.getInstance().getReference("Consultants")
        val user = mapOf<String, String>(
            "name" to name,
            "address" to address,
            "age" to age,
            "date" to date
        )

        database.child(number).updateChildren(user).addOnSuccessListener {

            binding.name.text.clear()
            binding.address.text.clear()
            binding.etnumber.text.clear()
            binding.age.text.clear()
            Toast.makeText(this, "Successfuly Updated", Toast.LENGTH_SHORT).show()


        }.addOnFailureListener {

            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()

        }
    }
}