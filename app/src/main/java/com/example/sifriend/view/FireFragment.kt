package com.example.sifriend.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sifriend.R
import com.example.sifriend.databinding.FragmentFireBinding
import kotlinx.android.synthetic.main.fragment_fire.*

class FireFragment : Fragment(R.layout.fragment_fire){
    private var fragmentBinding : FragmentFireBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFireBinding.bind(view)
        fragmentBinding = binding


            val popUp = PopupMenu(
                requireContext(),
                settings_icon
            )

            popUp.menu.add(Menu.NONE,0,1,"Edit Profile")
            popUp.menu.add(Menu.NONE,1,2,"Settings")

            popUp.setOnMenuItemClickListener { menuItem ->
                    val id = menuItem.itemId
                if(id==0){
                var intent = Intent(requireActivity(),EditProfileActivity::class.java)
                    startActivity(intent)

                }else if(id==1){
                   var intent = Intent(requireActivity(),SettingsActivity::class.java)
                    startActivity(intent)
                }
                false
            }

        binding.settingsIcon.setOnClickListener{
            popUp.show()
        }
    }



}