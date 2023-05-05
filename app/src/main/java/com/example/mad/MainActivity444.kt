package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ValueEventListener


class MainActivity444 : AppCompatActivity() {


    private lateinit var recyclerview : RecyclerView
    private lateinit var retrive_jobArrayList : ArrayList<retrive_job>
    private lateinit var db : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main444)

        recyclerview = findViewById(R.id.retriveRecycle)
        recyclerview.layoutManager = LinearLayoutManager(this)

        retrive_jobArrayList = arrayListOf()

        db = FirebaseDatabase.getInstance().getReference("Add_Job")

        db.addValueEventListener(object:ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot.exists()){
                    for(dataSnapShot in snapshot.children){
                        val user = dataSnapShot.getValue(retrive_job::class.java)
                        if(!retrive_jobArrayList.contains(user)){
                            retrive_jobArrayList.add(user!!)
                        }

                    }

                    val mAdapter = addjob_Addapter(retrive_jobArrayList)
                    recyclerview.adapter = mAdapter
                    //recyclerview.adapter= addjob_Addapter(retrive_jobArrayList)

                    mAdapter.setOnItemClickListener(object : addjob_Addapter.onItemClickListener{
                        override fun onItemClick(position: Int) {

                           val intent = Intent(this@MainActivity444, MainActivity888::class.java)

                            //put extras
                            intent.putExtra("jobId",retrive_jobArrayList[position].jobId)
                            intent.putExtra("possition",retrive_jobArrayList[position].possition)
                            intent.putExtra("addree",retrive_jobArrayList[position].addree)
                            intent.putExtra("comName",retrive_jobArrayList[position].comName)
                            intent.putExtra("basicSal",retrive_jobArrayList[position].basicSal)
                            intent.putExtra("allowance",retrive_jobArrayList[position].allowance)
                            intent.putExtra("addree",retrive_jobArrayList[position].addree)
                            intent.putExtra("main",retrive_jobArrayList[position].main)
                            intent.putExtra("addi",retrive_jobArrayList[position].addi)
                            intent.putExtra("ot",retrive_jobArrayList[position].ot)
                            intent.putExtra("vacasis",retrive_jobArrayList[position].vacasis)
                            startActivity( intent)

                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity444, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        val NEXT = findViewById<ImageView>(R.id.imageView10)
        NEXT.setOnClickListener {
            val intent = Intent(this, MainActivity111::class.java)
            startActivity(intent)

        }

        val NEXT2 = findViewById<Button>(R.id.new1)
        NEXT2.setOnClickListener {
            val intent = Intent(this, MainActivity111::class.java)
            startActivity(intent)

        }
    }

}
