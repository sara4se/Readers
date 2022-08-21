package com.example.readers.ui.auth.splash.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.readers.R
import com.example.readers.databinding.FragmentSplashBinding
import com.example.readers.ui.auth.login.view.LoginFragment
import com.example.readers.ui.auth.splash.viewmodel.SplashViewModel
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    val viewModel: SplashViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()
        binding.apply {


            Glide.with(requireContext())
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Book-icon-bible.png/900px-Book-icon-bible.png?20201013125426")
                .into(imagevId)
        }
    }

    private fun observe() {

        lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {

                    viewModel.navigateToLogin.collect {
                        parentFragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, LoginFragment.newInstance())
                            .commit()
                    }

                }


            }


        }

    }
    companion object {
        fun newInstance() = SplashFragment()
    }
}