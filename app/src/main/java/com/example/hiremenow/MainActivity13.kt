package com.example.hiremenow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class MainActivity13 : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var usersArrayList: ArrayList<Users>
    private lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main13)
        // Help button to navigate to the main activity
        val helpButton: ImageView = findViewById(R.id.help33btn)
        helpButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


// RecyclerView to display the list of customers
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this )

        usersArrayList = arrayListOf()
        // Firebase database reference for the "Customers" node
        database = FirebaseDatabase.getInstance().getReference("Customers")
        // ValueEventListener to retrieve customer data from the database
        database.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(dataSnapShot in snapshot.children){
                        val user = dataSnapShot.getValue(Users::class.java)
                        if(!usersArrayList.contains(user)){
                            usersArrayList.add(user!!)
                        }
                    }
                    // Set up the RecyclerView adapter with the retrieved customer data
                    val mAdaptor= MyAdapter(usersArrayList)
                    recyclerView.adapter = mAdaptor
                    // Set click listener for each item in the RecyclerView
                    mAdaptor.setOnItemClickListener(object:MyAdapter.OnItemClickListener{
                        override fun onItemClick(position: Int) {
                           val intent = Intent(this@MainActivity13,MainActivity2::class.java)
                            //put extras to pass customer data to MainActivity2
                            intent.putExtra("customerId",usersArrayList[position].customerId)
                            intent.putExtra("customerName",usersArrayList[position].customerName)
                            intent.putExtra("customerEmail",usersArrayList[position].customerEmail)
                            intent.putExtra("customerPhone",usersArrayList[position].customerPhone)
                            intent.putExtra("customerSubject",usersArrayList[position].customerSubject)
                            intent.putExtra("customerMessage",usersArrayList[position].customerMessage)
                            startActivity(intent)
                        }

                    })




                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Display error message if the ValueEventListener was cancelled
                Toast.makeText(this@MainActivity13,error.toString(), Toast.LENGTH_SHORT).show()
            }
        })


    }
}