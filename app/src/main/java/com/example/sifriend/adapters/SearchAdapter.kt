package com.example.sifriend.adapters

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sifriend.ChatActivity
import com.example.sifriend.R
import com.example.sifriend.model.Post
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_item.view.*

class SearchAdapter(options: FirestoreRecyclerOptions<Post>) : FirestoreRecyclerAdapter<Post, SearchAdapter.MyViewHolder>(options) {

    class MyViewHolder(var view : View) : RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_item_search,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Post) {
        holder.view.card_name.text = model.name
        holder.view.card_yas.text = model.age
        holder.view.card_konum.text = model.konum
        Picasso.get().load(model.imageUrl).into(holder.view.card_image)
        holder.view.call.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:5316969906")
            it.context.startActivity(intent)
        }
        holder.view.sendMessage.setOnClickListener {
            val intent = Intent(it.context,ChatActivity::class.java)
            intent.putExtra("data",model.postId)
            it.context.startActivity(intent)
        }

    }
}


