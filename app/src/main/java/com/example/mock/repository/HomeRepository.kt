package com.example.mock.repository

import android.app.Application
import com.example.mock.entity.Food
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeRepository(application: Application) {
    private val db = Firebase.firestore

    fun getProductFromFireStore() {
        var foodList: ArrayList<Food> = ArrayList()
        var popularList: ArrayList<Food> = ArrayList()
        db.collection("food")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    foodList.clear()
                    for (document in it.result!!) {
                        foodList.add(
                            Food(
                                document.id.toString(),
                                document.get("name").toString(),
                                document.get("amount").toString().toInt(),
                                document.get("description").toString(),
                                document.get("available").toString().toBoolean(),
                                document.get("image").toString(),
                                document.get("discount").toString().toInt(),
                                document.get("ordertimes").toString().toInt(),
                                document.get("price").toString().toDouble(),
                                document.get("sort").toString()
                            )
                        )
                    }
                    foodList.sortByDescending { it.ordertimes }
                    popularList.clear()
                    for (i in 0..4) {
                        popularList.add(foodList[i])
                        //                        dealList.add(foodList[i])
                    }

                }
            }
    }
}