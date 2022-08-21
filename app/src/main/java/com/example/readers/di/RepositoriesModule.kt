package com.example.readers.di

import com.example.readers.data.repository.AuthRepository
import com.example.readers.data.repository.RoomsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {


    @Singleton
    @Provides
    fun provideAuthRepository(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore)
    : AuthRepository = AuthRepository(firebaseAuth, firebaseFirestore)

    @Singleton
    @Provides
    fun provideRoomRepository(firebaseFirestore: FirebaseFirestore): RoomsRepository =
        RoomsRepository(firebaseFirestore)



}