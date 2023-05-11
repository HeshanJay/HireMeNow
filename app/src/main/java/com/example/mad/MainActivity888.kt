package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class MainActivity888 : AppCompatActivity() {

    private lateinit var bbJobId :TextView   //declaring views according to the input views
    private lateinit var bbCompanyName :TextView
    private lateinit var bbPossition :TextView
    private lateinit var bbMainReq :TextView
    private lateinit var bbbasicSal :TextView
    private lateinit var bballowance :TextView
    private lateinit var bbaddree :TextView
    private lateinit var bbaddi :TextView
    private lateinit var bbaot :TextView
    private lateinit var bbvacasis :TextView
    private lateinit var bbtot :TextView
    private lateinit var bbbbtn1 :Button
    private lateinit var bbbtn2 :Button


    override fun onCreate(savedInstanceState: Bundle?) {  //when activity created
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main888)

        val NEXT = findViewById<ImageView>(R.id.add7)  // direct for add_job form
        NEXT.setOnClickListener {
            val intent = Intent(this, MainActivity111::class.java)
            startActivity(intent)
        }

        initView() // initialising views
        setValuesToViews() // setting values to views

        bbbbtn1.setOnClickListener {  //for submit button operations
            openUpdateDialog(
                intent.getStringExtra("jobId").toString(),
                intent.getStringExtra("comName").toString(),
                intent.getStringExtra("tot").toString()

                )
        }

        bbbtn2.setOnClickListener { //for delete button operations
            deleteRecord(
                intent.getStringExtra("jobId").toString()
            )
        }

    }

    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Add_Job").child(id)  // db name and according to its id
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"Job Data deleted",Toast.LENGTH_LONG).show()//toast messeges for deleting

            val intent = Intent(this, MainActivity444 ::class.java) //when delete where to goes
            finish()
            startActivity(intent)
        }.addOnFailureListener{error ->

            Toast.makeText(this,"Deleting Err ${error.message}",Toast.LENGTH_LONG).show()
            //display error messege
        }
    }

    private fun initView(){
        bbJobId = findViewById(R.id.aajobId)                // find id
        bbCompanyName = findViewById(R.id.aaCompanyname)
        bbPossition = findViewById(R.id.aaposition)
        bbMainReq = findViewById(R.id.aaMainREQ)
        bbaddi = findViewById(R.id.aaAddREQ)
        bbbasicSal = findViewById(R.id.aaBasicSal)
        bballowance = findViewById(R.id.aaAllow)
        bbaddree = findViewById(R.id.aacompanyadr)
        bbaot = findViewById(R.id.aaOT)
        bbvacasis = findViewById(R.id.avacancy)
        bbtot = findViewById(R.id.aatott)
        bbbbtn1 = findViewById(R.id.edit5)
        bbbtn2 = findViewById(R.id.delete5)

    }


    private fun setValuesToViews(){
        bbJobId.text = intent.getStringExtra("jobId")             // set values
        bbCompanyName.text = intent.getStringExtra("comName")
        bbPossition.text = intent.getStringExtra("possition")
        bbMainReq.text = intent.getStringExtra("main")
        bbaddi.text = intent.getStringExtra("addi")
        bbbasicSal.text = intent.getStringExtra("basicSal")
        bballowance.text = intent.getStringExtra("allowance")
        bbaddree.text = intent.getStringExtra("addree")
        bbaot.text = intent.getStringExtra("ot")
        bbvacasis.text = intent.getStringExtra("vacasis")
        bbtot.text = intent.getStringExtra("tot")

    }


    private fun openUpdateDialog(
        jobId: String, //id get as it is
        comName: String,
        tot: String
    ) {
        // Create an AlertDialog and inflate the custom layout

        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_addjob, null)

        mDialog.setView(mDialogView)

        // Find the EditText fields in the dialog layout


//        val cJobid = mDialogView.findViewById<TextView>(R.id.caajobid)
        val cPosition = mDialogView.findViewById<EditText>(R.id.caaposition)
        val cCompanyAddress = mDialogView.findViewById<EditText>(R.id.caacompanyadr)
        val cVacancy = mDialogView.findViewById<EditText>(R.id.cavacancy)
        val cCompanyName = mDialogView.findViewById<EditText>(R.id.caaCompanyname)
        val cMainReq = mDialogView.findViewById<EditText>(R.id.caaMainREQ)
        val cAddReq = mDialogView.findViewById<EditText>(R.id.caaAddREQ)
        val cBasicSal = mDialogView.findViewById<EditText>(R.id.caaBasicSal)
        val cAllowance = mDialogView.findViewById<EditText>(R.id.caaAllow)
        val cOT = mDialogView.findViewById<EditText>(R.id.caaOT)
//        val ctotal = mDialogView.findViewById<EditText>(R.id.caatott)

        val btnConfirm = mDialogView.findViewById<Button>(R.id.cedit5)

        // Set the initial values of the EditText fields with the received intent extras


//        cJobid.setText(intent.getStringExtra("jobId").toString())
        cPosition.setText(intent.getStringExtra("possition").toString())
        cCompanyAddress.setText(intent.getStringExtra("addree").toString())
        cVacancy.setText(intent.getStringExtra("vacasis").toString())
        cCompanyName.setText(intent.getStringExtra("comName").toString())
        cMainReq.setText(intent.getStringExtra("main").toString())
        cAddReq.setText(intent.getStringExtra("addi").toString())
        cBasicSal.setText(intent.getStringExtra("basicSal").toString())
        cAllowance.setText(intent.getStringExtra("allowance").toString())
        cOT.setText(intent.getStringExtra("ot").toString())
//        ctotal.setText(intent.getStringExtra("tot").toString())

        mDialog.setTitle("Updating $comName Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnConfirm.setOnClickListener {

            //call the updateJobData function with updated values
            updateJobData(
                jobId,
                cPosition.text.toString(),
                cCompanyAddress.text.toString(),
                cVacancy.text.toString(),
                cCompanyName.text.toString(),
                cMainReq.text.toString(),
                cAddReq.text.toString(),
                cBasicSal.text.toString(),
                cAllowance.text.toString(),
                cOT.text.toString(),
//                ctotal.text.toString()
                tot

            )

            Toast.makeText(applicationContext, " The Job Data Updated.",Toast.LENGTH_LONG).show()

            //seting updated data
//            bbJobId.text = jobId
            bbCompanyName.text = cCompanyName.text.toString()
            bbPossition.text = cPosition.text.toString()
            bbMainReq.text = cMainReq.text.toString()
            bbaddi.text = cAddReq.text.toString()
            bbbasicSal.text = cBasicSal.text.toString()
            bballowance.text = cAllowance.text.toString()
            bbaddree.text = cCompanyAddress.text.toString()
            bbaot.text = cOT.text.toString()
            bbvacasis.text = cVacancy.text.toString()
//            bbtot.text =ctotal.text.toString()
            alertDialog.dismiss()
        }

    }

    private fun updateJobData(
        id: String,
        CompanyName: String,
        Possition: String,
        MainReq: String,
        AddiReq: String,
        Basic: String,
        Allowance: String,
        Address: String,
        OT: String,
        Vacancies: String,
        Vtot: String
    ){
        //det db reference and update specific data record
        val dbRef = FirebaseDatabase.getInstance().getReference("Add_Job").child(id)
        val jobInfo = addJobModel(id,CompanyName,Possition,MainReq,AddiReq,Basic,Allowance,Address,OT,Vacancies,Vtot)
        dbRef.setValue(jobInfo)
    }


}