package com.example.sifriend.adapters


import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sifriend.R
import com.example.sifriend.model.Post
import com.google.android.material.snackbar.Snackbar
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
        holder.view.call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:5316969906")
            it.context.startActivity(intent)
        }
        holder.view.sendMessage.setOnClickListener {

        }

    }

    override fun getItemCount(): Int {
       return list.size
    }

}
