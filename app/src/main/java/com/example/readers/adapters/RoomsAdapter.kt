package com.example.readers.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.readers.R
import com.example.readers.models.Rooms

class RoomsAdapter(val rooms: ArrayList<Rooms>) : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {
    lateinit var onItemClickListener : OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycle_item_rooms, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: RoomsAdapter.ViewHolder, position: Int) {

        val imageViewRooms = holder.roomImage
        Glide.with(holder.roomImage.context)
            .load(rooms[position].link)
            .into(imageViewRooms)

        val textViewType = holder.tv_roomType
        textViewType.text = rooms[position].roomType
        //  holder.tv_roomType.text = rooms[position].roomType

        holder.cardNews.setOnClickListener {
            onItemClickListener.onItemClick(position)
        //    itemClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int = rooms.size

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val cardNews: CardView = listItemView.findViewById<CardView>(R.id.card_rooms)
        val roomImage: ImageView= listItemView.findViewById<ImageView>(R.id.room_image)
        val tv_roomType: TextView= listItemView.findViewById<TextView>(R.id.tv_roomtype)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }



}