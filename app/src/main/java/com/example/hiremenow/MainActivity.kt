package com.example.hiremenow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var namecus:EditText
    private lateinit var emailcus:EditText
    private lateinit var phonecus:EditText
    private lateinit var subjecus:EditText
    private lateinit var Messagecus:EditText
    private lateinit var subbmitbtn:Button

    private lateinit var dbRef:DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        namecus = findViewById(R.id.namecus)
        emailcus = findViewById(R.id.emailcus)
        phonecus = findViewById(R.id.phonecus)
        subjecus = findViewById(R.id.subjecus)
        Messagecus = findViewById(R.id.Messagecus)
        subbmitbtn = findViewById(R.id.subbmitbtn)


        dbRef= FirebaseDatabase.getInstance().getReference("Customers")

        subbmitbtn.setOnClickListener{
            saveCustomerData()
        }



        val profileButton: ImageButton = findViewById(R.id.profilebtn)
        profileButton.setOnClickListener {
            val intent = Intent(this, MainActivity12::class.java)
            startActivity(intent)
        }





    }
    private fun saveCustomerData(){
        //getting values
        val customerName = namecus.text.toString()
        val customerEmail= emailcus.text.toString()
        val customerPhone= phonecus.text.toString()
        val customerSubject= subjecus.text.toString()
        val customerMessage= Messagecus.text.toString()

        if(customerName.isEmpty()){
            namecus.error = "Please enter name"
        }
        if(customerEmail.isEmpty()){
            emailcus.error = "Please enter email"
        }
        if(customerPhone.isEmpty()){
            phonecus.error = "Please enter phone"
        }
        if(customerSubject.isEmpty()){
            subjecus.error = "Please enter subject"
        }
        if(customerMessage.isEmpty()){
            Messagecus.error = "Please enter message"
        }

        val customerId = dbRef.push().key!!

        val customer = CustomerModel(customerId,customerName,customerEmail,customerPhone,
        customerSubject,customerMessage)

        dbRef.child(customerId).setValue(customer)
            .addOnCompleteListener{
                Toast.makeText(this,"Data inserted sucessfully",Toast.LENGTH_LONG).show()

                namecus.text.clear()
                emailcus.text.clear()
                phonecus.text.clear()
                subjecus.text.clear()
                Messagecus.text.clear()

            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()

            }


    }

}