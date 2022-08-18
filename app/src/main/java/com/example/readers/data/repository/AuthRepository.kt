package com.example.readers.data.repository

import com.example.readers.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthRepository  @Inject constructor(
    private val firebaseAuth: FirebaseAuth
){
//Flow is sending the data from layer to layer
    fun signUp(email: String, password: String) : Flow<Resource<String>> =
        channelFlow {
        send(Resource.loading())
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                launch {
                    if (task.isSuccessful) {
                        send(Resource.success(firebaseAuth.currentUser?.uid!!))
                    }else {
                        send(Resource.error(task.exception?.message ?: "Authentication Failed"))
                    }
                    close()
                }
            }
        awaitClose()
    }
}