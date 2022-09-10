package com.example.mock.repository

import android.app.Application
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FireStoreRepository(application: Application) {
    private val db = Firebase.firestore

}
