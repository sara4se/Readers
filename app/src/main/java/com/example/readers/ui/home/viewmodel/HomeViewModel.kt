package com.example.readers.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etimad.android.businessapp.data.local.prefs.PreferencesUtility
import com.example.readers.data.models.Rooms
import com.example.readers.data.models.SavedRoom
import com.example.readers.data.repository.RoomsRepository
import com.example.readers.utils.Constants
import com.example.readers.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository,
    private val preferencesUtility: PreferencesUtility
) : ViewModel() {

    private val _rooms = MutableSharedFlow<List<SavedRoom>>()
    val roomsCommunitis = _rooms.asSharedFlow()

    private val _showLoading = MutableSharedFlow<Boolean>()
    val showLoading = _showLoading.asSharedFlow()

    private val _hideLoading = MutableSharedFlow<Boolean>()
    val hideLoading = _hideLoading.asSharedFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    fun getSavedRoomVM() {

        val uId = preferencesUtility.getString(Constants.U_ID)
        roomsRepository.getSavedRoom(uId).onEach { result ->

            when (result.status) {

                Resource.Status.LOADING -> {
                    _showLoading.emit(true)
                }

                Resource.Status.SUCCESS -> {
                    _hideLoading.emit(true)
                    _rooms.emit(result.data!!)
                }

                Resource.Status.ERROR -> {
                    _hideLoading.emit(true)
                    _error.emit(result.message!!)
                }
            }
        }.launchIn(viewModelScope)
    }
}
