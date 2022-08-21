package com.example.readers.ui.auth.signup.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.etimad.android.businessapp.data.local.prefs.PreferencesUtility
import com.example.readers.data.repository.AuthRepository
import com.example.readers.utils.BaseViewModel
import com.example.readers.utils.Constants
import com.example.readers.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject



@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val preferencesUtility: PreferencesUtility
) : BaseViewModel() {

    private val _navigateToHome = MutableSharedFlow<Boolean>()
    val navigateToHome = _navigateToHome.asSharedFlow()

    fun signUp(email: String, password: String) {

        authRepository.signUp(email, password).onEach { result ->

            when (result.status) {

                Resource.Status.LOADING -> {
                    showLoading.emit(true)
                }

                Resource.Status.SUCCESS -> {

                    addUserToDb(result.data!!.email!!, result.data.uid)
                    preferencesUtility.setString(Constants.U_ID, result.data.uid)
                    Log.d("User ADD", "i add user")
                    _navigateToHome.emit(true)
                    }

                Resource.Status.ERROR -> {
                    hideLoading.emit(true)
                    error.emit(result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun addUserToDb(email: String, uId: String) {

        authRepository.addUserToDb(email, uId).onEach { result ->

            when (result.status) {

                Resource.Status.LOADING -> {
                    showLoading.emit(true)
                }

                Resource.Status.SUCCESS -> {
//                    preferencesUtility.setString(Constants.U_ID, uId)
                    hideLoading.emit(true)
                   // _navigateToHome.emit(true)
                }

                Resource.Status.ERROR -> {
                    hideLoading.emit(true)
                    error.emit(result.message!!)
                }
            }
        }.launchIn(viewModelScope)

    }
    }
