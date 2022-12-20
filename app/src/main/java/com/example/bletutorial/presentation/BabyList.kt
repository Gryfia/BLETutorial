package com.example.bletutorial.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bletutorial.R
import com.example.bletutorial.data.adapter.BabyAdapter
import com.example.bletutorial.util.SQLiteHelper

class BabyList : Activity() {


    private lateinit var btnAdd : Button

    private lateinit var dbHelper : SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var builder : AlertDialog.Builder
    private var adapter : BabyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.baby_list)

        initView()
        initRecyclerView()

        dbHelper = SQLiteHelper(this)
        builder = AlertDialog.Builder(this)

        val babyList = dbHelper.getAllBaby()
        adapter?.addItems(babyList)

        adapter?.setOnClickDelete { baby ->
            deleteBaby(baby.NIK)
        }

        btnAdd.setOnClickListener {
            val intent = Intent(this@BabyList, AddBaby::class.java)
            startActivity(intent)
        }

        adapter?.setOnClickItem { baby ->
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.add_umur, null)
            val editText = dialogLayout.findViewById<EditText>(R.id.edUmur)

            builder.setView(dialogLayout)
            builder.setTitle("Masukkan Umur")
            builder.setPositiveButton("OK") { dialog, which ->

                if (editText.text.toString().isEmpty() || editText.text.toString() == "") {
                    Toast.makeText(this, "Umur tidak boleh kosong", Toast.LENGTH_SHORT).show()
                } else {
                    val umur = editText.text.toString().toInt()
                    val intent = Intent(this@BabyList, ScaleBaby::class.java)
                    intent.putExtra("NIK", baby.NIK)
                    intent.putExtra("umur", umur)
                    startActivity(intent)
                }

            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                dialog.dismiss()
            }
            builder.create()
            builder.show()



        }

    }

    private fun deleteBaby(NIK: String){

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete Baby")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") { dialog, _ ->
            dbHelper.deleteBaby(NIK)
            dbHelper.deleteWeight(NIK)
            initRecyclerView()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.cancel()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BabyAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView(){
        btnAdd = findViewById(R.id.btnAdd)
        recyclerView = findViewById(R.id.recyclerView)
    }



}