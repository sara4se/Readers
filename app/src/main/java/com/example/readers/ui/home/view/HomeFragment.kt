package com.example.readers.ui.home.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.readers.R
import com.example.readers.data.models.Community
import com.example.readers.data.models.SavedRoom
import com.example.readers.databinding.FragmentHomeBinding
import com.example.readers.ui.adapters.RoomsAdapter
import com.example.readers.ui.community.view.AddRoomToCommunity
import com.example.readers.ui.home.viewmodel.HomeViewModel
import com.example.readers.ui.profile.view.ProfileFavoriteRoom
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val savedRoom2 = mutableListOf<SavedRoom>()
    lateinit var rvRoom: RecyclerView
    val CommunityList = ArrayList<Community>()

    lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment


        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //region recycle view
        CommunityList.add(
            Community(
                1,
                "historical",
                "https://i.pinimg.com/736x/5b/8b/78/5b8b781e2a913ef6acbe7876dbc8b13b.jpg"
            )
        )
        CommunityList.add(
            Community(
                2,
                "philosophy",
                "https://i.pinimg.com/474x/5a/b0/3f/5ab03feb20b6bc38e7c8811e11241d37.jpg"
            )
        )
        CommunityList.add(
            Community(
                3,
                "Sciences",
                "https://i.pinimg.com/564x/e3/36/b8/e336b83bdcbaee5f97d9452f3bed261a.jpg"
            )
        )
        CommunityList.add(
            Community(
                4,
                "literature",
                "https://i.pinimg.com/474x/40/d7/01/40d7018406ef78d48aca3042799c80e4.jpg"
            )
        )
        CommunityList.add(
            Community(
                5,
                "religion",
                "https://i.pinimg.com/474x/da/b5/47/dab54705115119b819ec4ea9c477aeaa.jpg"
            )
        )
        CommunityList.add(
            Community(
                6,
                "Arts",
                "https://i.pinimg.com/564x/d2/df/51/d2df510673d368c7c383c808abbe1d4b.jpg"
            )
        )
        CommunityList.add(
            Community(
                7,
                "Novels",
                "https://i.pinimg.com/564x/25/c2/a3/25c2a3b93c6c2a1d26e0b260fa93d22f.jpg"
            )
        )


        rvRoom = view.findViewById(R.id.recycle_rooms)
        rvRoom.layoutManager = GridLayoutManager(this@HomeFragment.requireContext(), 2)


        val roomsAdapter = RoomsAdapter(CommunityList)
        rvRoom.adapter = roomsAdapter

        roomsAdapter.onItemClickListener = object : RoomsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
//
//                val intent =
//                    Intent(this@HomeFragment.requireContext(), ProfileFavoriteRoom::class.java)
//                startActivity(intent)
            }
        }//endregion

        binding.apply {

            btAddNewRoom.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .add(R.id.fragment_container1, AddRoomToCommunity.newInstance())
                    .addToBackStack("AddPlaceFragment")
                    .commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =HomeFragment()
    }
}
