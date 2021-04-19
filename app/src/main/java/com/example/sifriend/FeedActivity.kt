package com.example.sifriend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.example.sifriend.view.FavoriteFragment
import com.example.sifriend.view.FireFragment
import com.example.sifriend.view.HomeFragment
import com.example.sifriend.view.SearchFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {
    private lateinit var toolbar : MaterialToolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        toolbar = MaterialToolbar(this)
        setSupportActionBar(toolbar)



        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bell -> {

                    true
                }
                R.id.search ->{

                    true
                }
                R.id.favorite ->{
                    true
                }
                R.id.fire -> {
                    true
                }

                else -> false
            }
    }
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())

                    true
                }
                R.id.search -> {
                    replaceFragment(SearchFragment())

                    true
                }
                R.id.favorite ->{
                    replaceFragment(FavoriteFragment())
                    true
                }
                R.id.fire -> {
                    replaceFragment(FireFragment())

                    true
                }
                else ->{
                    false
                }
            }
        }
}
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, fragment)
        transaction.commit()
    }


}