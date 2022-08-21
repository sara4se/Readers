package com.example.readers.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


open class BaseViewModel : ViewModel() {

    protected val showLoading = MutableSharedFlow<Boolean>()
    val showLoadingFlow = showLoading.asSharedFlow()

    protected val hideLoading = MutableSharedFlow<Boolean>()
    val hideLoadingFlow = hideLoading.asSharedFlow()

    protected val error = MutableSharedFlow<String>()
    val errorFlow = error.asSharedFlow()

}