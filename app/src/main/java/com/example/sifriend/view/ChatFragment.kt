package com.example.sifriend.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sifriend.R
import com.example.sifriend.adapters.ChatAdapter
import com.example.sifriend.databinding.FragmentChatBinding
import com.example.sifriend.model.Messages
import com.example.sifriend.model.Post
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_edit_profile.editText
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private var fragmentBinding : FragmentChatBinding? = null
    private var db : FirebaseFirestore? = FirebaseFirestore.getInstance()
    private var auth : FirebaseAuth? = FirebaseAuth.getInstance()
    private var user = auth?.currentUser
    private var realtimeDatabase : FirebaseDatabase? = FirebaseDatabase.getInstance()
    var messageList = ArrayList<Messages>()
    private var adapter = ChatAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bind = FragmentChatBinding.bind(view)
         fragmentBinding = bind

        bind.chatRv.layoutManager = LinearLayoutManager(requireContext())
        bind.chatRv.adapter = adapter

        bind.chatSendMessage.setOnClickListener {
            val messageText = enterMessage.text.toString()
            //sendMessageFun(messageText)

        }

        //getMessages()

    }

   /* private fun getMessages() {

        db?.collection("Message")?.orderBy("messageOwn",Query.Direction.DESCENDING)?.addSnapshotListener { snapshot, e ->
            if(e != null){
                Snackbar.make(requireView(),"Error",2000).show()
            }
            else{
                if(snapshot != null) {
                    val messages = snapshot.documents

                    for (messageI in messages) {
                        val message = messageI.get("message").toString();
                        val user = messageI.get("messageOwn").toString()

                        val myMessage = Messages(message, user as String?, "2")

                        messageList.add(myMessage)
                        adapter.list = messageList
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }*/

    private fun sendMessageFun(messageText : String) {
/*
        val myMessage = Messages(messageText,"asddsa",user!!.uid)

        db?.collection("Message")?.add(myMessage)?.addOnCompleteListener { task->
            if(task.isSuccessful){
                Snackbar.make(requireView(),"Kaan",2000).show()
            }
        }?.addOnFailureListener { e->
            Snackbar.make(requireView(),"Hata",2000).show()
        }*/
    }
}