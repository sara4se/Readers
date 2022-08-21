package com.example.readers.di

import android.content.Context
import com.etimad.android.businessapp.data.local.prefs.PreferencesUtility
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Singleton
    @Provides
    fun providePreferencesUtility(@ApplicationContext applicationContext: Context): PreferencesUtility {
        return PreferencesUtility(
            applicationContext.getSharedPreferences(
                "my_prefs",
                Context.MODE_PRIVATE
            )
        )
    }

}