package com.example.readers.ui.community.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.etimad.android.businessapp.data.local.prefs.PreferencesUtility
import com.example.readers.data.repository.RoomsRepository
import com.example.readers.utils.Constants
import com.example.readers.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRoomsToCommunityViewModel @Inject constructor(
    private val roomsRepository: RoomsRepository,
    val preferencesUtility: PreferencesUtility
) : ViewModel() {

    private val _resetForm = MutableSharedFlow<Boolean>()
    val resetForm = _resetForm.asSharedFlow()

    private val _successAdd = MutableSharedFlow<Boolean>()
    val successAdd = _successAdd.asSharedFlow()

    private val _showLoading = MutableSharedFlow<Boolean>()
    val showLoading = _showLoading.asSharedFlow()

    private val _hideLoading = MutableSharedFlow<Boolean>()
    val hideLoading = _hideLoading.asSharedFlow()

    private val _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    fun addRoom(
        roomTitle: String,
        roomDesc: String,
    )
    {
          val uId = preferencesUtility.getString(Constants.U_ID)
          Log.d("Lunch scope", " $uId this is user id in viewModelScope")
//             if (id==null) {
//              _error.emit("You must select location")
//              return@launch
//          } else {

          roomsRepository.addRoom(
              uId= uId,
              roomTitle=  roomTitle,
              roomDesc=   roomDesc,
              ).onEach { result ->

                  when (result.status) {

                      Resource.Status.LOADING -> {
                          _showLoading.emit(true)
                      }

                      Resource.Status.SUCCESS -> {
                          _hideLoading.emit(true)
                          _successAdd.emit(true)
                          _resetForm.emit(true)
                          Log.d("Lunch scope", "  Resource.Status.SUCCESS")

                      }

                      Resource.Status.ERROR -> {
                          _hideLoading.emit(true)
                          _error.emit(result.message!!)
                      }
                  }

              }.launchIn(viewModelScope)
          }





}