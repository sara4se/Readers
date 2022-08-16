package com.example.readers.adapters

import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.readers.R
import com.example.readers.models.User


class UsersAdapter(val users: List<User>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    lateinit var onItemClickListener: OnItemClickListener
    private var listData: MutableList<User> = users as MutableList<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycle_item_user, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvName.text = users[position].name
        holder.tvAge.text = "${users[position].age}"
        holder.tvId.text = users[position].id

        holder.cardNews.setOnClickListener {
            onItemClickListener.onItemClicked(position)
        }


    }

    override fun getItemCount(): Int = users.size

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
