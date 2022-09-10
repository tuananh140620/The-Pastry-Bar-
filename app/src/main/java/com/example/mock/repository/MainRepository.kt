package com.example.mock.repository

import android.app.Application
import android.content.ContentValues
import android.util.Log
import com.example.mock.entity.Food
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainRepository(application: Application) {

    private val db = Firebase.firestore
    fun getProductFromFireStore(): ArrayList<Food> {
        val list = arrayListOf<Food>()
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(ContentValues.TAG, "${document.id} => ${document.data}")
                    val food = Food(
                        document.id,
                        document.data["name"].toString(),
                        document.data["amount"].toString().toInt(),
                        document.data["description"].toString(),
                        document.data["available"].toString().toBoolean(),
                        document.data["image"].toString(),
                        document.data["discount"].toString().toInt(),
                        document.data["ordertimes"].toString().toInt(),
                        document.data["price"].toString().toDouble(),
                        document.data["sort"].toString()
                    )
                    list.add(food)
                }
            }
        return list
    }
}