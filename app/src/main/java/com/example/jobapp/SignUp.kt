package com.example.jobapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.jobapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var binding:ActivitySignupBinding
    private lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signin.setOnClickListener {
            val intent = Intent(this,SignIn::class.java)
            startActivity(intent)
        }

        binding.signup.setOnClickListener{
            val email = binding.emailenter.text.toString()
            val pass = binding.passwordenter.text.toString()
            val confirmPass = binding.reenterpassword.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() &&  confirmPass.isNotEmpty()){
                if(pass == confirmPass){
                        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
                            if(it.isSuccessful){
                                    val intent = Intent(this,SignIn::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(this,it.exception.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                }else{
                    Toast.makeText(this,"Password is not matching", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty fields are not allowed!!", Toast.LENGTH_SHORT).show()
            }

        }
    }
}