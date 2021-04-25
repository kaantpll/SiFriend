package com.example.sifriend.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sifriend.R
import com.example.sifriend.adapters.SearchAdapter
import com.example.sifriend.databinding.FragmentSearchBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var fragmentBind : FragmentSearchBinding? = null
    private var db : FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bind = FragmentSearchBinding.bind(view)
        fragmentBind = bind

        var a  = bind.searchView.toString()

        val postListQ = db.collection("Posts")
        val query = postListQ.whereEqualTo("nickname",a)
        Snackbar.make(requireView(),query.toString(),5000).show()

    }
}