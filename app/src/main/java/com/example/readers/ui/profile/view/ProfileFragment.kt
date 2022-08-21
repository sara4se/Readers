package com.example.readers.ui.profile.view

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.etimad.android.businessapp.data.local.prefs.PreferencesUtility
import com.example.readers.R
import com.example.readers.data.models.Community
import com.example.readers.databinding.ActivityProfileFavoriteRoomBinding
import com.example.readers.databinding.FragmentHomeBinding
import com.example.readers.databinding.FragmentProfileBinding
import com.example.readers.ui.adapters.RoomsAdapter
import com.example.readers.ui.home.view.HomeFragment
import com.example.readers.ui.home.viewmodel.HomeViewModel
import com.example.readers.ui.navigate.view.NavigationActivity
import com.example.readers.ui.profile.viewmodel.ProfileViewModel
import com.example.readers.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var favRoom: RecyclerView
    lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//            binding.apply {
//                username.setText(viewModel.getPrefUid())
//            }
        val rooms = ArrayList<Community>()
        rooms.add(
            Community(
                1,
                "historical",
                "https://i.pinimg.com/736x/5b/8b/78/5b8b781e2a913ef6acbe7876dbc8b13b.jpg"
            )
        )
        rooms.add(
            Community(
                2,
                "philosophy",
                "https://i.pinimg.com/474x/5a/b0/3f/5ab03feb20b6bc38e7c8811e11241d37.jpg"
            )
        )
        rooms.add(
            Community(
                3,
                "Sciences",
                "https://i.pinimg.com/564x/e3/36/b8/e336b83bdcbaee5f97d9452f3bed261a.jpg"
            )
        )
        rooms.add(
            Community(
                4,
                "literature",
                "https://i.pinimg.com/474x/40/d7/01/40d7018406ef78d48aca3042799c80e4.jpg"
            )
        )
        rooms.add(
            Community(
                5,
                "religion",
                "https://i.pinimg.com/474x/da/b5/47/dab54705115119b819ec4ea9c477aeaa.jpg"
            )
        )
        rooms.add(
            Community(
                6,
                "Arts",
                "https://i.pinimg.com/564x/d2/df/51/d2df510673d368c7c383c808abbe1d4b.jpg"
            )
        )
        rooms.add(
            Community(
                7,
                "Novels",
                "https://i.pinimg.com/564x/25/c2/a3/25c2a3b93c6c2a1d26e0b260fa93d22f.jpg"
            )
        )
        favRoom = view.findViewById(R.id.recycle_favRooms)
        favRoom.layoutManager = LinearLayoutManager(this@ProfileFragment.requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val roomsAdapter = RoomsAdapter(rooms)
        favRoom.adapter = roomsAdapter



        roomsAdapter.onItemClickListener = object : RoomsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {

                val intent =
                    Intent(this@ProfileFragment.requireContext(), NavigationActivity::class.java)
                startActivity(intent)
            }
        }


        viewModel.getSavedRoomVM()



        observe()
    }

    private fun observe() {

        lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.apply {
                    //region places
                    launch {
                        viewModel.roomsCommunitis.collect { rooms ->

                            rooms.forEach {

                                titleOfRoom.setText("${it.title}")
                                decOfRoom.setText("${it.desc}")

                            }
//                                savedRoom2.add(it)
//                                Log.d("observe: Saved Room :",savedRoom2.toString())

                        }
                    }


                }
            }
            //endregion

            //region showLoading
            launch {
                viewModel.showLoading.collect {
                    binding.pb.visibility = View.VISIBLE
                }
            }
            //endregion

            //region hideLoading
            launch {
                viewModel.hideLoading.collect {
                    binding.pb.visibility = View.GONE
                }
            }
            //endregion

            //region error
            launch {
                viewModel.error.collect {
                    Toast.makeText(
                        this@ProfileFragment.requireContext(),
                        it,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            //endregion

        }


    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}