package com.example.mock.repository

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.mock.adapter.CheckoutAdapter
import com.example.mock.entity.Order
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import com.google.android.gms.tasks.OnSuccessListener
import kotlinx.android.synthetic.main.fragment_order_checkout.*


class CheckoutRepository (application: Application) {

    lateinit var db: FirebaseFirestore

    val mAuth = FirebaseAuth.getInstance()

     fun createTheHistoryOrder(checkoutList:ArrayList<Order>, resId : Int,type:String,resName:String,totalPrice:Float,uid:String ) {
         val current = LocalDateTime.now()
         val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
         val formatted = current.format(formatter)

         //create the history that has all the items and can be checked
         val dbData = hashMapOf(
             "items" to checkoutList,
             "resId" to resId,
             "type" to type,
             "time" to formatted,
             "branch_name" to resName,
             "total_price" to totalPrice,
             "uid" to uid
         )
         db.collection("history")

             .add(dbData)
             .addOnSuccessListener(OnSuccessListener<DocumentReference> {
                 Log.d("mylog", "successful")

             })
             .addOnFailureListener { e ->
                 Log.w("mylog", "Error deleting document", e)

             }
     }
//    fun clearTheCurrentCheckoutList(){
//
//        for (id in idList) {
//            db.collection("orders").document("$id")
//                .delete()
//                .addOnSuccessListener {
//                    Log.d(
//                        "mylog",
//                        "DocumentSnapshot successfully deleted!"
//                    )
//                }
//                .addOnFailureListener { e -> Log.w("mylog", "Error deleting document", e) }
//        }
//        idList.clear()
//        checkoutList.clear()
//    }

//     fun update(adapter: CheckoutAdapter) {
//        totalPrice = 0f
//
//        db.collection("orders")
//            .whereEqualTo("uid", uid)
//            .get()
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    if (task.result!!.isEmpty) {
//                        Log.d("reach", "Don't have history")
//                        grand_total.text = "Total: $0.00"
//                        checkout_button.visibility = View.INVISIBLE
//                    } else {
//                        Log.d("reach", "Have history")
//
//                        checkoutList.clear()
//                        idList.clear()
//                        for (document in task.result!!) {
//                            checkoutList.add(
//                                Order(
//                                    uid,
//                                    document.get("foodname").toString(),
//                                    document.get("time").toString(),
//                                    document.get("amount").toString().toInt(),
//                                    document.get("totalprice").toString().toFloat()
//                                )
//                            )
//                            idList.add(document.id)
//                            Log.d("mylog", idList.toString())
//                            totalPrice+= document.get("totalprice").toString().toFloat()
//                            count +=1
//
//                        }
//                        checkoutList.sortByDescending { it.time }
//                        Log.d("reach", "size " + checkoutList.size)
//                        grand_total.text = "Total: $ $totalPrice"
//                        checkout_button.visibility = View.VISIBLE
//                        adapter.notifyDataSetChanged()
//                    }
//                } else {
//                    println("failed")
//                }
//            }
//    }


}