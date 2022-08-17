package com.example.readers.data.remote.dao

import androidx.room.*
import com.example.readers.data.models.Rooms

@Dao
interface RoomsDao {

    @Insert
    fun insertRooms(room: ArrayList<Rooms>)

    @Update
    fun updateRooms(room: Rooms)

    @Delete
    fun deleteRooms(room: Rooms)

    @Query("SELECT * FROM room_table")
    fun getAllRooms(): List<Rooms>
}