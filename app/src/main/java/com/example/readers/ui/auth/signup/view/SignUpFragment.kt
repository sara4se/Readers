package com.example.readers.ui.auth.signup.view

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
import com.example.readers.databinding.FragmentSignUpBinding
import com.example.readers.ui.auth.login.view.LoginFragment
import com.example.readers.ui.auth.signup.viewmodel.SignUpViewModel
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% WHAT!!!
        binding = FragmentSignUpBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            btnSignUp.setOnClickListener {
                viewModel.signUp(
                    etEmail.text.toString(),
                    etPassWord.text.toString()
                )
            }

            tvLogin.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, LoginFragment.newInstance())
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
                            this@SignUpFragment.requireContext(),
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
        fun newInstance() = SignUpFragment()
    }
}