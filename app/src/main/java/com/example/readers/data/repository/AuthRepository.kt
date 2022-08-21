package com.example.readers.data.repository

import android.widget.Toast
import com.example.readers.utils.Constants
import com.example.readers.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) {


    fun signUp(email: String, password: String): Flow<Resource<FirebaseUser>> = channelFlow {

        send(Resource.loading())
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                launch {

                    if (task.isSuccessful) {
                        send(Resource.success(firebaseAuth.currentUser!!))
                    } else {
                        send(Resource.error(task.exception?.message ?: "Authentication Failed"))
                    }

                    close()
                }
            }

        awaitClose()
    }

    fun signIn(email: String, password: String): Flow<Resource<FirebaseUser>> = channelFlow {

        send(Resource.loading())
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                launch {

                    if (task.isSuccessful) {
                        send(Resource.success(firebaseAuth.currentUser!!))
                    } else {
                        send(Resource.error(task.exception?.message ?: "Authentication Failed"))
                    }

                    close()
                }
            }

        awaitClose()
    }

    fun addUserToDb(email: String, uId: String): Flow<Resource<Boolean>> = channelFlow {

        val userData = hashMapOf(
            Constants.FIRE_STORE_EMAIL to email
        )

        firebaseFirestore.collection(Constants.FIRE_STORE_USERS)
            .document(uId)
            .set(userData)
            .addOnSuccessListener {
                launch {
                    send(Resource.success(true))
                    close()
                }
            }
            .addOnFailureListener { exception ->
                launch {
                    send(Resource.error(exception.message ?: "Authentication Failed"))
                    close()
                }
            }

        awaitClose()

    }

}