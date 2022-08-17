package com.example.readers.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readers.R
import com.example.readers.data.remote.dao.RoomsDao
import com.example.readers.RoomsDatabase
import com.example.readers.ui.adapters.RoomsAdapter
import com.example.readers.data.models.Rooms
import com.example.readers.ui.home.view.HomePage

class ProfileFavoriteRoom : AppCompatActivity() {

    lateinit var favRoom: RecyclerView
    lateinit var roomsDatabase: RoomsDatabase
    lateinit var roomDao: RoomsDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_favorite_room)

        val rooms = ArrayList<Rooms>()
        rooms.add(
            Rooms(
                1,
                "historical",
                "https://i.pinimg.com/736x/5b/8b/78/5b8b781e2a913ef6acbe7876dbc8b13b.jpg"
            )
        )
        rooms.add(
            Rooms(
                2,
                "philosophy",
                "https://i.pinimg.com/474x/5a/b0/3f/5ab03feb20b6bc38e7c8811e11241d37.jpg"
            )
        )
        rooms.add(
            Rooms(
                3,
                "Sciences",
                "https://i.pinimg.com/564x/e3/36/b8/e336b83bdcbaee5f97d9452f3bed261a.jpg"
            )
        )
        rooms.add(
            Rooms(
                4,
                "literature",
                "https://i.pinimg.com/474x/40/d7/01/40d7018406ef78d48aca3042799c80e4.jpg"
            )
        )
        rooms.add(
            Rooms(
                5,
                "religion",
                "https://i.pinimg.com/474x/da/b5/47/dab54705115119b819ec4ea9c477aeaa.jpg"
            )
        )
        rooms.add(
            Rooms(
                6,
                "Arts",
                "https://i.pinimg.com/564x/d2/df/51/d2df510673d368c7c383c808abbe1d4b.jpg"
            )
        )
        rooms.add(
            Rooms(
                7,
                "Novels",
                "https://i.pinimg.com/564x/25/c2/a3/25c2a3b93c6c2a1d26e0b260fa93d22f.jpg"
            )
        )
        favRoom = findViewById(R.id.recycle_favRooms)
        favRoom.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val roomsAdapter = RoomsAdapter(rooms)
        favRoom.adapter = roomsAdapter

//
//        roomsDatabase = RoomsDatabase.getInstance(this)
//        roomDao = roomsDatabase.roomDao()

//        GlobalScope.launch {
//            roomDao.insertRooms(rooms)
//        }
//        GlobalScope.launch {
//            val allrooms = roomDao.getAllRooms()
//            allrooms.forEach {it->
//                Log.d("Room   :  ", "")
//            }
//        }
//
//
        roomsAdapter.onItemClickListener = object : RoomsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@ProfileFavoriteRoom, HomePage::class.java)
                val bundle = Bundle()
                bundle.putString("LINK", rooms[position].link)
                intent.putExtras(bundle)
                startActivity(intent)
//                }
            }
        }
    }
}
