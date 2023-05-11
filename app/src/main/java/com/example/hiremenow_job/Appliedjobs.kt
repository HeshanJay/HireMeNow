package com.example.hiremenow_job

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class Appliedjobs : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var appliedjobsList: ArrayList<Appjobs>
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appliedjobs)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        appliedjobsList = arrayListOf()

        database = FirebaseDatabase.getInstance().getReference("Job_Seekers")

        // Retrieve data from Firebase database
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (dataSnapshot in snapshot.children) {
                        val appjobs = dataSnapshot.getValue(Appjobs::class.java)
                        // Add each Appjobs object to the appliedjobsList if not already present
                        if (!appliedjobsList.contains(appjobs))
                            appliedjobsList.add(appjobs!!)
                    }

                    // Set up RecyclerView adapter with appliedjobsList data
                    val mAdaptor= MyAdapter(appliedjobsList)
                    recyclerView.adapter = mAdaptor

                    // Handle item click event in RecyclerView
                    mAdaptor.setOnItemClickListener(object: MyAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@Appliedjobs,AppliedJob_details::class.java)
                            // Put extras to pass customer data
                            intent.putExtra("jsId",appliedjobsList[position].jsId)
                            intent.putExtra("companyNamenJobTitle",appliedjobsList[position].companyNamenJobTitle)
                            intent.putExtra("jsName",appliedjobsList[position].jsName)
                            intent.putExtra("jsEmail",appliedjobsList[position].jsEmail)
                            intent.putExtra("jsPhone",appliedjobsList[position].jsPhone)
                            intent.putExtra("fileName",appliedjobsList[position].fileName)
                            startActivity(intent)
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Appliedjobs, error.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }
}
