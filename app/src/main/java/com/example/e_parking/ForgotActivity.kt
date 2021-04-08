package com.example.e_parking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_forgot.*

class ForgotActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)

        resetButton.setOnClickListener {
            val email: String = for_email.text.toString().trim { it <= ' ' }
            if (email.isEmpty()) {
                Toast.makeText(
                    this@ForgotActivity,
                    "Please enter your email adress",
                    Toast.LENGTH_LONG
                ).show()
            }else {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                this@ForgotActivity,
                                "Email sent successfuly to reset your password!",
                                Toast.LENGTH_LONG
                            ).show()
                            finish()
                    }else{
                            Toast.makeText(
                                this@ForgotActivity,
                                task.exception!!.message.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
            }
        }
    }
}}