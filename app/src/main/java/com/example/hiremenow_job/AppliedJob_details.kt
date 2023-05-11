package com.example.hiremenow_job

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class AppliedJob_details : AppCompatActivity() {

    private lateinit var tvJsId: TextView
    private lateinit var tvJobtitle: TextView
    private lateinit var tvFname: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvpdf: TextView
    private lateinit var tv_Edit: Button
    private lateinit var tv_Delete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applied_job_details)

        val applyjobIcon = findViewById<ImageView>(R.id.applyjob_icon3)
        applyjobIcon.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        initView()
        setValuesToViews()

        tv_Edit.setOnClickListener{
            openUpdateDialog(
                intent.getStringExtra("jsId").toString(),
                intent.getStringExtra("companyNamenJobTitle").toString()
            )
        }

        tv_Delete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("jsId").toString()
            )
        }

    }

    //delete Records
    private fun deleteRecord(
        id: String
    ){
        // get reference to the database
        val dbRef = FirebaseDatabase.getInstance().getReference("Job_Seekers").child(id)
        // remove the value from the database
        val mTask = dbRef.removeValue()
        // add success and failure listeners
        mTask.addOnSuccessListener {
            Toast.makeText(this ,"Your data deleted",Toast.LENGTH_LONG).show()
            val intent = Intent(this,Appliedjobs::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{ error ->
            Toast.makeText(this ,"Deleting Err ${error.message}",Toast.LENGTH_LONG).show()
        }
    }

    private fun initView() {
        tvJsId = findViewById(R.id.tv_jsid)
        tvJobtitle = findViewById(R.id.tv_Jobtitle)
        tvFname = findViewById(R.id.tv_Fname)
        tvEmail = findViewById(R.id.tv_Email)
        tvPhone = findViewById(R.id.tv_Phone)
        tvpdf = findViewById(R.id.tv_pdf)
        tv_Edit = findViewById(R.id.tvEdit)
        tv_Delete = findViewById(R.id.tvDelete)
    }

    private fun setValuesToViews() {
        tvJsId.text = intent.getStringExtra("jsId")
        tvJobtitle.text = intent.getStringExtra("companyNamenJobTitle")
        tvFname.text = intent.getStringExtra("jsName")
        tvEmail.text = intent.getStringExtra("jsEmail")
        tvPhone.text = intent.getStringExtra("jsPhone")
        tvpdf.text = intent.getStringExtra("fileName")
    }

    private fun openUpdateDialog(
        jsId: String,
        companyNamenJobTitle:String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.activity_job_update,null)

        mDialog.setView(mDialogView)
// initialize EditText views for customer data
        val up_jobtitle = mDialogView.findViewById<EditText>(R.id.up_jobtitle)
        val up_name = mDialogView.findViewById<EditText>(R.id.up_name)
        val up_email = mDialogView.findViewById<EditText>(R.id.up_email)
        val up_phone = mDialogView.findViewById<EditText>(R.id.up_phone)
        val up_resume = mDialogView.findViewById<EditText>(R.id.up_resume)
// initialize update button
        val confirm_btn = mDialogView.findViewById<Button>(R.id.confirm_btn)
// set current customer data to EditText views
        up_jobtitle.setText( intent.getStringExtra("companyNamenJobTitle").toString())
        up_name.setText( intent.getStringExtra("jsName").toString())
        up_email.setText( intent.getStringExtra("jsEmail").toString())
        up_phone.setText( intent.getStringExtra("jsPhone").toString())
        up_resume.setText( intent.getStringExtra("fileName").toString())
// set dialog title with customer name
        mDialog.setTitle("Updating $companyNamenJobTitle Record")
// set update button click listener
        val alertDialog = mDialog.create()
        alertDialog.show()
// update the customer data in the database
        confirm_btn.setOnClickListener {
            updateCusData(
                jsId,
                up_jobtitle.text.toString(),
                up_name.text.toString(),
                up_email.text.toString(),
                up_phone.text.toString(),
                up_resume.text.toString()
            )
// show a toast message to indicate data has been updated
            Toast.makeText(applicationContext,"Your Data Updated", Toast.LENGTH_LONG).show()

            //we wre setting the updated data to our textviews
            // update the text views with the new data
            tvJobtitle.text= up_jobtitle.text.toString()
            tvFname.text= up_name.text.toString()
            tvEmail.text=  up_email.text.toString()
            tvPhone.text= up_phone.text.toString()
            tvpdf.text=  up_resume.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updateCusData(
        id:String,
        jobtitle:String,
        fname:String,
        email:String,
        cphone:String,
        cresume:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Job_Seekers").child(id)
        val cusInfo = JobseekerModel(id,jobtitle,fname,email,cphone,cresume)
        dbRef.setValue(cusInfo)
    }
}