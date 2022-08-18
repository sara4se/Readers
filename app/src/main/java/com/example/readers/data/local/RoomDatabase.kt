package com.example.readers

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.readers.data.models.Rooms
import com.example.readers.data.local.dao.RoomsDao


@Database(entities = [Rooms::class], version = 1)
abstract class RoomsDatabase : RoomDatabase(){

        abstract fun roomDao(): RoomsDao

        companion object {

            private var instance: RoomsDatabase? = null

            fun getInstance(context: Context): RoomsDatabase {

                if (instance == null) {

                    instance = Room
                        .databaseBuilder(context, RoomsDatabase::class.java, "room_database")
                        .build()

                }

                return instance!!
            }

        }


    }
