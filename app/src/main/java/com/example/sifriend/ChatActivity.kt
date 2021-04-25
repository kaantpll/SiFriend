package com.example.sifriend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sifriend.adapters.ChatAdapter
import com.example.sifriend.model.Messages
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_chat.*


class ChatActivity : AppCompatActivity() {


    private var db : FirebaseFirestore? = FirebaseFirestore.getInstance()
    private var auth : FirebaseAuth? = FirebaseAuth.getInstance()
    private var user : FirebaseUser? = auth?.currentUser
    var messageList = ArrayList<Messages>()
    private var adapter = ChatAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        var senderId = user?.uid

        var data = intent.getStringExtra("data")
        Toast.makeText(this,data.toString(),Toast.LENGTH_SHORT).show()
        var receiverId =data

        chatRv.layoutManager = LinearLayoutManager(this)
        chatRv.adapter = adapter

        chatSendMessage.setOnClickListener {
            val messageText = enterMessage.text.toString()
            if (senderId != null) {
                if (receiverId != null) {
                    sendMessageFun(messageText,senderId,receiverId)
                }
            }
        }

        getMessages()

    }

    private fun getMessages() {

        db?.collection("Message")?.orderBy("message",
                com.google.firebase.firestore.Query.Direction.DESCENDING)?.addSnapshotListener { snapshot, e ->
            if(e != null){
                Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()

            }
            else{
                if(snapshot != null) {
                    val messages = snapshot.documents

                    messageList.clear()

                    for (messageI in messages) {
                        val message = messageI.get("message").toString();
                        val messageId = messageI.get("messageId").toString()
                        val userId = messageI.get("receiverId").toString()

                        val myMessage = Messages(message,messageId,userId)

                        messageList.add(myMessage)
                        adapter.list = messageList
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun sendMessageFun(messageText: String, senderId: String,receiverId:String) {

        val myMessage = Messages(messageText,senderId,receiverId)

        db?.collection("Message")?.add(myMessage)?.addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(applicationContext,"Sending",Toast.LENGTH_SHORT).show()
            }
        }?.addOnFailureListener { e->
            Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()

        }
    }


}