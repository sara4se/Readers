package com.example.readers.ui.community.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.readers.R
import com.example.readers.databinding.FragmentAddRoomToCommunityBinding
import com.example.readers.ui.community.viewmodel.AddRoomsToCommunityViewModel
import com.example.readers.ui.navigate.view.NavigationActivity
import com.example.readers.ui.profile.view.ProfileFavoriteRoom
import com.example.readers.ui.profile.view.ProfileFragment
import com.example.readers.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class AddRoomToCommunity : Fragment() {

    private lateinit var binding: FragmentAddRoomToCommunityBinding
    private val viewModel: AddRoomsToCommunityViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddRoomToCommunityBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnadd.setOnClickListener {

                viewModel.addRoom(
                    roomTitle = etTitle.text.toString(),
                    roomDesc = etDesc.text.toString()
                )
                val uId =viewModel.preferencesUtility.getString(Constants.U_ID)
                Log.d("Lunch scope", " $uId this is user id")
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container1, ProfileFragment.newInstance())
                 //   .addToBackStack("AddPlaceFragment")
                    .commit()
            }
        }
        observe()
    }
    private fun observe() {

        lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                //region successAdd
                launch {
                    viewModel.successAdd.collect {
                        Toast.makeText(
                            this@AddRoomToCommunity.requireContext(),
                            "Your Community added successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                //endregion

                //region resetForm
                launch {
                    viewModel.resetForm.collect {
                        binding.apply {
                            etTitle.setText("")
                            etDesc.setText("")
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
                            this@AddRoomToCommunity.requireContext(),
                            it,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                //endregion

            }
        }
    }
    companion object {
        fun newInstance() = AddRoomToCommunity()
    }
}