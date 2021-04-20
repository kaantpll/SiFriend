package com.example.sifriend.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.sifriend.AddActivity
import com.example.sifriend.R
import com.example.sifriend.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var fragmentBinding : FragmentHomeBinding? =null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = FragmentHomeBinding.bind(view)
        fragmentBinding = binding

        /*
        binding..setOnClickListener {
            val intent = Intent(requireContext(), AddActivity::class.java)
            startActivity(intent)
        }*/

    }
}