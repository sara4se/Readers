package com.example.readers.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Community (

    val id: Int,
    val communitiesType: String,
    val link: String
)
