package com.example.sifriend.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sifriend.R
import com.example.sifriend.model.Post
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_item.view.*

class HomeAdapter(var list : ArrayList<Post>): RecyclerView.Adapter<HomeAdapter.MyViewHolder>(){

    class MyViewHolder(var view: View) : RecyclerView.ViewHolder(view)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.view.card_name.text = list[position].name
        holder.view.card_yas.text = list[position].age
        holder.view.card_konum.text = list[position].konum
        Picasso.get().load(list[position].imageUrl).into(holder.view.card_image)

    }

    override fun getItemCount(): Int {
       return list.size
    }
}