package com.example.bletutorial

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.compose.material.MaterialTheme
import com.example.bletutorial.ui.theme.BLETutorialTheme
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.bletutorial.presentation.BabyGraphList
import com.example.bletutorial.presentation.BabyList
import com.example.bletutorial.presentation.GrowthGraph

class MainActivity : Activity(), View.OnClickListener {

    private lateinit var addbaby : TextView
    private lateinit var growthgraph : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addbaby = findViewById(R.id.addbaby)
        growthgraph = findViewById(R.id.growthgraph)

        addbaby.setOnClickListener(this)
        growthgraph.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.addbaby -> {
                val intent = Intent(this@MainActivity, BabyList::class.java)
                startActivity(intent)
            }
            R.id.growthgraph -> {
                Toast.makeText(this, "Growth Graph", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, BabyGraphList::class.java)
                startActivity(intent)
            }
        }
    }
}
