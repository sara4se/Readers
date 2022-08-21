package com.example.readers.ui.auth.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel(){

    private val _navigateToLogin = MutableSharedFlow<Boolean>()
    val navigateToLogin = _navigateToLogin.asSharedFlow()

    init {
        delayAndNavigate()
    }

    private fun delayAndNavigate() {
        viewModelScope.launch {
            delay(3000)
            _navigateToLogin.emit(true)
        }
    }


}