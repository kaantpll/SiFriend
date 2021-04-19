package com.example.sifriend.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.sifriend.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val settings = listOf<String>(
            "Ayar 1",
            "Ayar 2",
            "Ayar 3",
            "Ayar 4",
            "Ayar 5",
        )

        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, settings)

        listView.adapter = adapter
    }
}