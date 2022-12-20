package com.example.bletutorial.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bletutorial.R
import com.example.bletutorial.data.adapter.BabyAdapter
import com.example.bletutorial.data.adapter.BabyGraphAdapter
import com.example.bletutorial.util.SQLiteHelper

class BabyGraphList : Activity() {


    private lateinit var btnAdd : Button

    private lateinit var dbHelper : SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter : BabyGraphAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.baby_graph_list)

        initView()
        initRecyclerView()

        dbHelper = SQLiteHelper(this)

        val babyList = dbHelper.getAllBaby()
        adapter?.addItems(babyList)

        adapter?.setOnClickItem { baby ->
            val intent = Intent(this@BabyGraphList, GrowthGraph::class.java)
            intent.putExtra("NIK", baby.NIK)
            startActivity(intent)

        }

    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BabyGraphAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView(){
        recyclerView = findViewById(R.id.recyclerView)
    }



}