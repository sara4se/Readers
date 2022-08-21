package com.example.readers.ui.auth.login.viewmodel

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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val preferencesUtility: PreferencesUtility
) : BaseViewModel() {


    private val _navigateToHome = MutableSharedFlow<Boolean>()
    val navigateToHome = _navigateToHome.asSharedFlow()

    fun signIn(email: String, password: String) {

        authRepository.signIn(email, password).onEach { result ->

            when (result.status) {

                Resource.Status.LOADING -> {
                    showLoading.emit(true)
                }

                Resource.Status.SUCCESS -> {
                    preferencesUtility.setString(Constants.U_ID, result.data?.uid!!)
                    hideLoading.emit(true)
                    _navigateToHome.emit(true)
                }

                Resource.Status.ERROR -> {
                    hideLoading.emit(true)
                    error.emit(result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }

}