package com.example.e_parking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if(currentUser != null) {
            startActivity(Intent(this@LoginActivity, UserProfileActivity::class.java))
            finish()
        }

        login()

    }
    private fun login(){

         loginButton.setOnClickListener{
             if (TextUtils.isEmpty(email.text.toString())){
                 email.setError("Please enter your email!")
                 return@setOnClickListener
             }else if(TextUtils.isEmpty(pass.text.toString())){
                 pass.setError("Please enter password!")
                 return@setOnClickListener
             }

             auth.signInWithEmailAndPassword(email.text.toString(), pass.text.toString())
                 .addOnCompleteListener {
                     if(it.isSuccessful){
                         startActivity(Intent(this@LoginActivity, UserProfileActivity::class.java))
                         finish()

                     }else{
                         Toast.makeText(this@LoginActivity, "Login failed", Toast.LENGTH_LONG).show()
                     }
                 }
         }

        sign_up.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))

        }

        forgot.setOnClickListener{
            startActivity(Intent(this@LoginActivity, ForgotActivity::class.java))
        }

    }
}