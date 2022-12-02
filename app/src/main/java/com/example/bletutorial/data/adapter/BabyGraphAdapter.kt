package com.example.bletutorial.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bletutorial.R
import com.example.bletutorial.data.model.BabyModel

class BabyGraphAdapter : RecyclerView.Adapter<BabyGraphAdapter.BabyViewHolder>() {
    private var babyList: ArrayList<BabyModel> = ArrayList()
    private var onClickItem: ((BabyModel) -> Unit)? = null

    fun addItems(babyList: ArrayList<BabyModel>) {
        this.babyList = babyList
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (BabyModel) -> Unit) {
        this.onClickItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BabyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_items_baby_graph, parent, false)
    )
    override fun onBindViewHolder(holder: BabyViewHolder, position: Int) {
        val baby = babyList[position]
        holder.bindView(baby)
        holder.itemView.setOnClickListener {
            onClickItem?.invoke(baby)
        }
    }

    override fun getItemCount(): Int {
        return babyList.size
    }

    class BabyViewHolder (var view: View) : RecyclerView.ViewHolder(view) {
        private var tvNIK: TextView = view.findViewById(R.id.tvNIK)
        private var tvNama: TextView = view.findViewById(R.id.tvNama)

        fun bindView (baby: BabyModel){
            tvNIK.text = baby.NIK
            tvNama.text = baby.nama
        }
    }

}