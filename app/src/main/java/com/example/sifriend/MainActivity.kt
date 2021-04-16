package com.example.sifriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signInBtn.setOnClickListener {
            var email = email_text.text.toString()
            var password = password_text.text.toString()

            SignIn(email,password)
        }
        signUpBtn.setOnClickListener {
            var email = email_text.text.toString()
            var password = password_text.text.toString()

            SignUp(email,password)
        }
    }
    fun SignIn(email:String, password:String)
    {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if(task.isSuccessful){Toast.makeText(this,"başarılı",Toast.LENGTH_LONG).show()}
        }.addOnFailureListener { e->Toast.makeText(this,e.message.toString(),Toast.LENGTH_LONG).show() }

    }
    fun SignUp(email:String,password:String)
    {
     mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
         if(task.isSuccessful){
             Toast.makeText(this,"Başarılı",Toast.LENGTH_LONG).show()

             //saveDatabase()

         }

        }.addOnFailureListener {
         e->
         Toast.makeText(this,e.message.toString(),Toast.LENGTH_LONG).show()

     }

    }
}