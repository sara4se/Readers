package com.example.readers.ui.auth.login.view

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
import com.bumptech.glide.Glide
import com.example.readers.R
import com.example.readers.databinding.FragmentLoginBinding
import com.example.readers.ui.auth.login.viewmodel.LoginViewModel
import com.example.readers.ui.auth.signup.view.SignUpFragment
import com.example.readers.ui.navigate.view.NavigationActivity
import com.example.readers.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            Glide.with(this@LoginFragment)
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Book-icon-bible.png/900px-Book-icon-bible.png?20201013125426")
                .into(ivId)

            btLogin.setOnClickListener {
                viewModel.signIn(
                    etUsername.text.toString(),
                    etPassword.text.toString()
                )
            }

            loginOrSignup.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SignUpFragment.newInstance())
                    .commit()
            }
        }
        observe()
    }

    private fun observe() {

        lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                //region navigateToHome
                launch {
                    viewModel.navigateToHome.collect { uId ->
                        Log.d("SignUpFragmentTAG", "navigate $uId")
                        startActivity(
                            Intent(
                                this@LoginFragment.requireContext(),
                                NavigationActivity::class.java
                            )
                        )
                        requireActivity().finish()
                    }
                }
                //endregion

                //region showLoading
                launch {
                    viewModel.showLoadingFlow.collect {
                        binding.pb.visibility = View.VISIBLE
                    }
                }
                //endregion

                //region hideLoading
                launch {
                    viewModel.hideLoadingFlow.collect {
                        binding.pb.visibility = View.GONE
                    }
                }
                //endregion

                //region error
                launch {
                    viewModel.errorFlow.collect {
                        Toast.makeText(
                            this@LoginFragment.requireContext(),
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
        fun newInstance() = LoginFragment()
            }
        }

//
//    companion object {
//        fun newInstance(name: String) = LoginFragment().apply {
//            arguments = Bundle().apply {
//                putString("NAME", name)
//            }
//        }
