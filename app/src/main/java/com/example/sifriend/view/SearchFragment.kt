package com.example.sifriend.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sifriend.R
import com.example.sifriend.adapters.SearchAdapter
import com.example.sifriend.databinding.FragmentSearchBinding
import com.example.sifriend.model.Post
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var fragmentBind: FragmentSearchBinding? = null
    private var db: FirebaseFirestore = FirebaseFirestore.getInstance()

    private lateinit var adapter :  SearchAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bind = FragmentSearchBinding.bind(view)
        fragmentBind = bind


        bind.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                return true
            }

        })

    }

    fun search(query: String?) {
        if (query != null) {
            var querya = db.collection("Posts").whereEqualTo("name", query).orderBy("name")
            val option = FirestoreRecyclerOptions.Builder<Post>().setQuery(querya, Post::class.java).build()
            adapter = SearchAdapter(option)
            searchRv.layoutManager = LinearLayoutManager(requireContext())
            
            searchRv.adapter = adapter
            adapter.notifyDataSetChanged()
        }
/*
    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()
        if (adapter != null) {
            adapter!!.stopListening()
        }
    }*/
    }
}