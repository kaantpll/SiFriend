package com.example.sifriend

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.HashMap

class MainActivity : AppCompatActivity() {
    private var mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private var user : FirebaseUser? = mAuth.currentUser
    private var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* if(user !=  null){
            val intent = Intent(this,FeedActivity::class.java)
            startActivity(intent)
        }*/

        signInBtn.setOnClickListener {
            var email = email_text.text.toString()
            var password = password_text.text.toString()

            SignIn(email,password)
        }
        signUpBtn.setOnClickListener {
            var email = email_text.text.toString()
            var password = password_text.text.toString()
            var nickname = nicknameText.text.toString()
            var age = age_text.text.toString()
            SignUp(email,password,nickname,age)
        }
    }
    fun SignIn(email:String, password:String)
    {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if(task.isSuccessful){Toast.makeText(this,"başarılı",Toast.LENGTH_LONG).show()
                val intent = Intent(this,FeedActivity::class.java)
                startActivity(intent)
            }
        }.addOnFailureListener { e->Toast.makeText(this,e.message.toString(),Toast.LENGTH_LONG).show() }

    }
    fun SignUp(email:String,password:String,nickName : String,age:String)
    {
     mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
         if(task.isSuccessful){
             Toast.makeText(this,"Başarılı",Toast.LENGTH_LONG).show()

             saveDatabase(email,password,nickName,age)

         }

        }.addOnFailureListener {
         e->
         Toast.makeText(this,e.message.toString(),Toast.LENGTH_LONG).show()

     }

    }

    private fun saveDatabase(userEmail: String, userPassword: String,nickname:String,age:String) {

        val city = hashMapOf(
                "email" to userEmail,
                "password" to userPassword,
                "nickname" to nickname,
                "age" to age,
                "registerDate" to Timestamp(Date())
        )

        db.collection("users")
            .add(city)
            .addOnSuccessListener { print("Success") }
            .addOnFailureListener { print("Fail") }

    }
}