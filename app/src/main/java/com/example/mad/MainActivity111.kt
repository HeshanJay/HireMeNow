package com.example.mad

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity111 : AppCompatActivity() {
                                                     //defining variables acording to input types
    private lateinit var jobPossi :EditText
    private lateinit var commAddre :EditText
    private lateinit var noofVac :EditText
    private lateinit var commName :EditText
    private lateinit var mainReq :EditText
    private lateinit var addiReq :EditText
    private lateinit var basicSal :EditText
    private lateinit var allowance :EditText
    private lateinit var otSal :EditText
    private lateinit var total :TextView
    private lateinit var totAmount :TextView
    private lateinit var cancel1 :Button
    private lateinit var submit1 :Button
    private lateinit var profile1 : ImageView   //for vector assets


    private lateinit var dbRef: DatabaseReference  //database reference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main111)

        jobPossi = findViewById(R.id.JobPosition)     //mentioning the variables for perticular id's
        commAddre = findViewById(R.id.companyAddress)
        noofVac = findViewById(R.id.noVacncies)
        commName = findViewById(R.id.companyName)
        mainReq = findViewById(R.id.mainReq)
        addiReq = findViewById(R.id.addreq)
        basicSal = findViewById(R.id.basicsal)
        allowance = findViewById(R.id.allo)
        otSal = findViewById(R.id.ot)
//        total = findViewById(R.id.tottext)
        totAmount = findViewById(R.id.amount)
        cancel1 = findViewById(R.id.cancel2)
        submit1 = findViewById(R.id.submit2)

        profile1 = findViewById(R.id.p1)

        dbRef = FirebaseDatabase.getInstance().getReference("Add_Job") //database mentioning

        submit1.setOnClickListener {
            saveAddJob()                     // save the data according to on click listner
        }

        profile1.setOnClickListener {
            val intent = Intent(this, MainActivity333::class.java) // where to goes after submitting
            startActivity(intent)}


        cancel1.setOnClickListener { //page clearing on click function
            clearFields()
            Toast.makeText(this, "Page cleared.", Toast.LENGTH_SHORT).show()
        }



    }

    private fun clearFields() {  //page clearing
        jobPossi.text.clear()
        commAddre.text.clear()
        noofVac.text.clear()
        commName.text.clear()
        mainReq.text.clear()
        addiReq.text.clear()
        basicSal.text.clear()
        allowance.text.clear()
        otSal.text.clear()
    }
    private fun saveAddJob() {
        //getting values
        val Possition = jobPossi.text.toString()
        val Addree= commAddre.text.toString()
        val Vacasis = noofVac.text.toString()
        val ComName = commName.text.toString()                //defining the types
        val Main = mainReq.text.toString()
        val Addi = addiReq.text.toString()
        val BasicSal = basicSal.text.toString()
        val Allowance = allowance.text.toString()
        val Ot = otSal.text.toString()

// Check if Vacasis is a valid integer
        val isVacasisValid = Vacasis.toIntOrNull() != null    //validations to wrappers
        if (!isVacasisValid) {
            // Show error message to the user and prevent form submission
            Toast.makeText(this, "Please enter a valid number for the number of vacancies.", Toast.LENGTH_SHORT).show()
            return
                                //toast messeges
        }

// Check if BasicSal, Allowance, and Ot are valid numbers (integers or floats)
        val isBasicSalValid = BasicSal.toFloatOrNull() != null                  //validations to wrappers
        val isAllowanceValid = Allowance.toFloatOrNull() != null
        val isOtValid = Ot.toFloatOrNull() != null
        if (!isBasicSalValid || !isAllowanceValid || !isOtValid) {
            // Show error message to the user and prevent form submission
            Toast.makeText(this, "Please enter a valid number for the salary and allowance fields.", Toast.LENGTH_SHORT).show()
            return
        }

// All input is valid, proceed with form submission to the database


// All input is valid, calculate total salary
        val tot = (BasicSal.toFloat() * Allowance.toFloat() * Ot.toFloat()).toString() //calculation function
                                                                                       //convert strings to number and floating then do the calculation and find value


        if(Possition.isEmpty()){                           //if fields not filled
            jobPossi.error = " Please enter possition"
        }
        if(Addree.isEmpty()){
            commAddre.error = " Please enter Address"
        }
        if(Vacasis.isEmpty()){
            noofVac.error = " Please enter Vacancies"
        }
        if(ComName.isEmpty()){
            commName.error = " Please enter Company Name"
        }
        if(Main.isEmpty()){
            mainReq.error = " Please enter Requirement1"
        }
        if(Addi.isEmpty()){
            addiReq.error = " Please enter Requirement2"
        }
        if(BasicSal.isEmpty()){
            basicSal.error = " Please enter Basis"
        }
        if(Allowance.isEmpty()){
            allowance.error = " Please enter Allowance"
        }
        if(Ot.isEmpty()){
            otSal.error = " Please enter OT"
        }

        val jobId = dbRef.push().key!!

        val addJob = addJobModel(jobId, Possition, Addree, Vacasis, ComName, Main, Addi, BasicSal, Allowance, Ot ,tot) // crete object

        dbRef.child(jobId).setValue(addJob)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully. ", Toast.LENGTH_LONG).show()

                jobPossi.text.clear()
                commAddre.text.clear()
                noofVac.text.clear()
                commName.text.clear()
                mainReq.text.clear()          // clear the form when a data record passed
                addiReq.text.clear()
                basicSal.text.clear()
                allowance.text.clear()
                otSal.text.clear()

            }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show() //toast error messege


            }



    }

}