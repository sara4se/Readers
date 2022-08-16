package com.example.readers.models

import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room_table")
data class Rooms(@PrimaryKey val id: Int, @ColumnInfo(name = "room_Type") val roomType:String, val link: String)
