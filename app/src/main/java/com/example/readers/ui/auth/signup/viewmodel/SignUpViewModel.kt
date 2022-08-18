package com.example.readers.ui.auth.signup.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readers.data.repository.AuthRepository
import com.example.readers.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _navigateToHome = MutableSharedFlow<String>()
    val navigateToHome = _navigateToHome.asSharedFlow()

    private val _showLoading = MutableSharedFlow<Boolean>()
    val showLoading = _showLoading.asSharedFlow()

    private val _hideLoading = MutableSharedFlow<Boolean>()
    val hideLoading = _hideLoading.asSharedFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()


    fun signUp(email: String, password: String) {

        authRepository.signUp(email, password).onEach { result ->

            when(result.status) {

                Resource.Status.LOADING -> {
                    _showLoading.emit(true)
                }

                Resource.Status.SUCCESS -> {
                    _hideLoading.emit(true)
                    _navigateToHome.emit(result.data!!)
                }

                Resource.Status.ERROR -> {
                    _hideLoading.emit(true)
                    _error.emit(result.message!!)
                }
            }


        }.launchIn(viewModelScope)

    }


}