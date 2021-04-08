

package com.example.e_parking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null

 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        register()
    }

    private fun register(){

        regButton.setOnClickListener{

            if (TextUtils.isEmpty(Fname.text.toString())){
                Fname.setError("Please enter your last name")
                return@setOnClickListener
            }else if(TextUtils.isEmpty(Lname.text.toString())) {
                Lname.setError("Please enter your last name")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(reg_email.text.toString())) {
                reg_email.setError("Please enter your email")
                return@setOnClickListener
            }else if (TextUtils.isEmpty(password.text.toString())) {
                password.setError("Please enter your ID number")
                return@setOnClickListener
            }
            auth.createUserWithEmailAndPassword(reg_email.text.toString(),password.text.toString() )
                    .addOnCompleteListener{
                        if(it.isSuccessful){
                            val currentUser = auth.currentUser
                            val currentUserDb = databaseReference?.child((currentUser?.uid!!))
                            currentUserDb?.child("firstname")?.setValue(Fname.text.toString())
                            currentUserDb?.child("lastname")?.setValue(Lname.text.toString())
                            currentUserDb?.child("idnumber")?.setValue(ID.text.toString())
                            currentUserDb?.child("email")?.setValue(reg_email.text.toString())


                            Toast.makeText(this@RegisterActivity, "Registration succes!", Toast.LENGTH_LONG).show()
                            finish()
                        } else {
                            Toast.makeText(
                                    this@RegisterActivity,
                                    "Registration Failed, try agian!",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }
        }

        account.setOnClickListener{
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }

    }

}