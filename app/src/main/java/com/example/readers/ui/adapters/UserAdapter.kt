package com.example.readers.ui.adapters

import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.readers.R
import com.example.readers.data.models.SavedRoom
import com.example.readers.data.models.UserS


class UsersAdapter(val room_: List<SavedRoom>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    lateinit var onItemClickListener: OnItemClickListener
    private var listData: MutableList<SavedRoom> = room_ as MutableList<SavedRoom>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycle_item_user, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvName.text = room_[position].id
        holder.tvAge.text = room_[position].title
        holder.tvId.text = room_[position].desc

        holder.cardNews.setOnClickListener {
            onItemClickListener.onItemClicked(position)
        }


    }

    override fun getItemCount(): Int = room_.size

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val cardNews = listItemView.findViewById<CardView>(R.id.card_news)

        val tvName = listItemView.findViewById<TextView>(R.id.tv_name)
        val tvAge = listItemView.findViewById<TextView>(R.id.tv_age)
        val tvId = listItemView.findViewById<TextView>(R.id.tv_id)

    }
    fun deleteItem(index: Int){
        listData.removeAt(index)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int)
    }
}
