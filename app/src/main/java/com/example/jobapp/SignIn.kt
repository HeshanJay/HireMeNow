package com.example.jobapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jobapp.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth

class SignIn : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textview.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        binding.login.setOnClickListener {
            val email = binding.emailsignin.text.toString()
            val pass = binding.passwordsignin.text.toString()


            if(email.isNotEmpty() && pass.isNotEmpty() ){
                    firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent = Intent(this,Home::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

            }else{
                Toast.makeText(this,"Empty fields are not allowed!!", Toast.LENGTH_SHORT).show()
            }

        }
        }

    }
