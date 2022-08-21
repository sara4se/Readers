package com.example.readers.ui.adapters

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.readers.R
import com.example.readers.data.models.Community
import com.example.readers.data.models.Rooms

class RoomsAdapter(val communities: ArrayList<Community>) : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {
    lateinit var onItemClickListener : OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycle_item_rooms, parent, false)
        return ViewHolder(view)


    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val imageViewRooms = holder.roomImage
        Glide.with(holder.roomImage.context)
            .load(communities[position].link)
            .into(imageViewRooms)

        val textViewType = holder.tvRoomType
        textViewType.text = communities[position].communitiesType
        //  holder.tv_roomType.text = rooms[position].roomType

        holder.cardNews.setOnClickListener {
            onItemClickListener.onItemClick(position)
        //    itemClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = communities.size

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val cardNews: CardView = listItemView.findViewById<CardView>(R.id.card_rooms)
        val roomImage: ImageView= listItemView.findViewById<ImageView>(R.id.room_image)
        val tvRoomType: TextView= listItemView.findViewById<TextView>(R.id.tv_roomtype)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }



}