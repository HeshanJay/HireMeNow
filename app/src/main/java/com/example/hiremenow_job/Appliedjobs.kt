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
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (dataSnapshot in snapshot.children){
                        val appjobs = dataSnapshot.getValue(Appjobs::class.java)
                        if (!appliedjobsList.contains(appjobs))
                            appliedjobsList.add(appjobs!!)
                    }
                    val mAdaptor= MyAdapter(appliedjobsList)
                    recyclerView.adapter = mAdaptor

                    mAdaptor.setOnItemClickListener(object: MyAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@Appliedjobs,AppliedJob_details::class.java)
                            //put extras to pass customer data
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

//                recyclerView.adapter = MyAdapter(appliedjobsList)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Appliedjobs, error.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }
}