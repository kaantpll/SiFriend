package com.example.sifriend.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sifriend.AddActivity
import com.example.sifriend.R
import com.example.sifriend.adapters.HomeAdapter
import com.example.sifriend.databinding.FragmentHomeBinding
import com.example.sifriend.listeners.ClickCardComponent
import com.example.sifriend.model.Post
import com.example.sifriend.utils.OnItemClickListener
import com.example.sifriend.utils.addOnItemClickListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home){

    private var fragmentBinding : FragmentHomeBinding? =null
    private var mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    var postList = ArrayList<Post>()
    private  var adapter = HomeAdapter(arrayListOf())
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = FragmentHomeBinding.bind(view)
        fragmentBinding = binding

        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.adapter = adapter

        binding.rv.addOnItemClickListener(object :OnItemClickListener{
            override fun onItemClicked(position: Int, view: View) {
               position
            }

        })

        getAllPost()
    }

    private fun getAllPost(){
        db.collection("Posts").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener{ snapshot, exception ->


            if(exception != null){
                Snackbar.make(requireView(),"Hata",2000)
            }
            else{
                if(snapshot != null){
                    val posts = snapshot.documents

                    for(post in posts){
                        val name = post.get("name").toString()
                        val konum = post.get("konum").toString()
                        val age = post.get("age").toString()
                        val pictureUrl = post.get("pictureUrl").toString()
                        val reason = post.get("reason").toString()

                        var postInstance = Post(age,konum,name,pictureUrl,reason)

                        postList.add(postInstance)
                        adapter.list = postList
                    }
                        adapter.notifyDataSetChanged()
                }
            }
        }
    }


}