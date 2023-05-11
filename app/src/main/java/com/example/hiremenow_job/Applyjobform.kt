package com.example.hiremenow_job

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class Applyjobform : AppCompatActivity() {

    private lateinit var cnjobtitle: EditText
    private lateinit var us_name: EditText
    private lateinit var us_email: EditText
    private lateinit var us_phone: EditText
    private lateinit var submit1: Button

    private lateinit var dbRef: DatabaseReference

    val PDF : Int = 0
    lateinit var uri : Uri
    lateinit var mStorage : StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applyjobform)

        cnjobtitle = findViewById<EditText>(R.id.companyNjobtitle)
        us_name = findViewById(R.id.cus_name)
        us_email = findViewById(R.id.cus_email)
        us_phone = findViewById(R.id.cus_phone)
        submit1 = findViewById(R.id.submit1)

        dbRef = FirebaseDatabase.getInstance().getReference("Job_Seekers")

        submit1.setOnClickListener {
            saveDataToDatabase(uri)
        }

        val pdfBtn = findViewById<View>(R.id.cv_upload) as Button

        val myBtn1 = findViewById<Button>(R.id.cancel2)
        myBtn1.setOnClickListener {
            val intent = Intent(this, Jobdescription::class.java)
            startActivity(intent)
        }

        val profileIcon = findViewById<ImageView>(R.id.profile_icon3)
        profileIcon.setOnClickListener {
            val intent = Intent(this, Profile_menu::class.java)
            startActivity(intent)
        }

        mStorage = FirebaseStorage.getInstance().getReference("Uploads")

        pdfBtn.setOnClickListener(View.OnClickListener {
                view: View? -> val intent = Intent()
            intent.type = "application/pdf"
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"Select PDF"), PDF)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == PDF) {
            data?.data?.let { uri ->
                val fileName = getFileName(uri)
                findViewById<TextView>(R.id.cs_resume).text = fileName

                submit1.setOnClickListener {
                    saveDataToDatabase(uri)
                }
            }
        }
    }

    /**
     * Extracts the file name from the given URI.
     *
     * @param uri The URI of the selected file.
     * @return The file name.
     */
    private fun getFileName(uri: Uri): String {
        var result = ""

        if (uri.scheme == "content") {
            contentResolver.query(uri, null, null, null, null)?.use {
                if (it.moveToFirst()) {
                    val columnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (columnIndex != -1) {
                        result = it.getString(columnIndex)
                    }
                }
            }
        }

        if (result.isEmpty()) {
            result = uri.lastPathSegment ?: ""
        }

        return result
    }

    /**
     * Saves the form data and uploaded file URI to the database.
     *
     * @param uri The URI of the uploaded file.
     */
    private fun saveDataToDatabase(uri: Uri) {
        val fileName = get
