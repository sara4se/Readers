package com.example.readers.ui.auth.signup.view

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
import com.example.readers.databinding.FragmentSignUpBinding
import com.example.readers.ui.auth.login.view.LoginFragment
import com.example.readers.ui.auth.signup.viewmodel.SignUpViewModel
import com.example.readers.ui.navigate.view.NavigationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class SignUpFragment : Fragment() {

    lateinit var binding: FragmentSignUpBinding
    val viewModel: SignUpViewModel by viewModels()

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
                Log.d("SignUpFragmentTAG", "i enterd")
            }

            tvLogin.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container1, LoginFragment.newInstance())
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
                        startActivity(Intent(this@SignUpFragment.requireContext(), NavigationActivity::class.java))
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
//    private fun observe() {
//
//        lifecycleScope.launch {
//
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
//
//                //region navigateToHome
//                launch {
//                    viewModel.navigateToHome.collect { uId ->
//                        Log.d("SignUpFragmentTAG", "navigate $uId")
//                    }
//                }
//                //endregion
//
//                //region showLoading
//                launch {
//                    viewModel.showLoadingFlow.collect {
//                        binding.pb.visibility = View.VISIBLE
//                    }
//                }
//                //endregion
//
//                //region hideLoading
//                launch {
//                    viewModel.hideLoadingFlow.collect {
//                        binding.pb.visibility = View.GONE
//                    }
//                }
//                //endregion
//
//                //region error
//                launch {
//                    viewModel.errorFlow.collect {
//                        Toast.makeText(
//                            this@SignUpFragment.requireContext(),
//                            it,
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
//                //endregion
//
//            }
//
//
//        }
//
//
//    }

    companion object {
        fun newInstance() = SignUpFragment()
    }
}