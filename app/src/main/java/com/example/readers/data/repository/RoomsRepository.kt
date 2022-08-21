package com.example.readers.data.repository


import com.example.readers.data.models.SavedRoom
import com.example.readers.utils.Constants
import com.example.readers.utils.Resource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RoomsRepository @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
) {

    fun addRoom(
        uId: String,
        roomTitle: String,
        roomDesc: String,
    ): Flow<Resource<Boolean>> = channelFlow {
    //    val userId = preferencesUtility.getString(Constants.U_ID)
        val roomData = hashMapOf(

            Constants.FIRE_STORE_ROOM_TITLE to roomTitle,
            Constants.FIRE_STORE_ROOM_DESCRIPTION to roomDesc,
        )

        firebaseFirestore.collection(Constants.FIRE_STORE_USERS)
            .document(uId)
            .collection(Constants.FIRE_STORE_COMMUNITY)
            .add(roomData)
            .addOnSuccessListener {
                launch {
                    send(Resource.success(true))
                    close()
                }
            }
            .addOnFailureListener { exception ->
                launch {
                    send(Resource.error(exception.message ?: "Add Rooms Failed"))
                    close()
                }
            }

        awaitClose()

    }

    fun getSavedRoom(uId: String): Flow<Resource<List<SavedRoom>>> = channelFlow {

        firebaseFirestore.collection(Constants.FIRE_STORE_USERS)
            .document(uId)
            .collection(Constants.FIRE_STORE_COMMUNITY)
            .addSnapshotListener { value, error ->

                launch {

                    if (error == null) {

                        val savedRoom = mutableListOf<SavedRoom>()
                        for (document in value?.documents!!) {

                            val title =
                                document.data?.getValue(Constants.FIRE_STORE_ROOM_TITLE) as String
                            val desc =
                                document.data?.getValue(Constants.FIRE_STORE_ROOM_DESCRIPTION) as String
                            val room = SavedRoom(document.id, title, desc)
                            savedRoom.add(room)
                        }

                        send(Resource.success(savedRoom))
                    } else {
                        send(Resource.error("Error in getting data"))
                    }

                    close()
                }
            }

        awaitClose()
    }

}