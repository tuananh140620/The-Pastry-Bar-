package com.example.mock.repository

import android.app.Application
import android.util.Log
import com.example.mock.adapter.OrderAdapter
import com.example.mock.entity.Food
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

class AppetizerRepository(application: Application) {
    private val db = Firebase.firestore
    var appeList: ArrayList<Food> = ArrayList()

    suspend fun getMenuAppetizer(): ArrayList<Food> {
        val adapter = OrderAdapter(appeList)
        val list = arrayListOf<Food>()
        return suspendCoroutineWithTimeout(10000L) { listP ->
            db.collection("food")
                .whereEqualTo("sort", "appetizer")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        if (task.result!!.isEmpty) {
                            Log.d("reach", "Don't have a Menu")
                        } else {
                            appeList.clear()
                            for (document in task.result!!) {
                                appeList.add(
                                    Food(
                                        document.get("id").toString(),
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
                            adapter.notifyDataSetChanged()
                        }
                    } else {
                        println("failed")
                    }
                    listP.resume(appeList)
                }

        }
    }

    private suspend inline fun <T> suspendCoroutineWithTimeout(
        timeout: Long,
        crossinline block: (Continuation<T>) -> Unit,
    ) = withTimeout(timeout) {
        suspendCancellableCoroutine(block = block)
    }
}