package com.example.mock.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.mock.R

import com.example.mock.adapter.OrderAdapter
import com.example.mock.entity.Food
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.fragment_appetizer.*

class AppetizerFragment : Fragment() {

    var appeList : ArrayList<Food> = ArrayList()
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            //.setTimestampsInSnapshotsEnabled(true)
            .build()
        db.firestoreSettings = settings
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appetizer, container, false)
    }

    override fun onStart(){
        super.onStart()
        val adapter = OrderAdapter(appeList)
        appe_recyclerView.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL,false)
        appe_recyclerView.adapter = adapter

        db.collection("food")
            .whereEqualTo("sort","appetizer")
            .get()
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    if(task.result!!.isEmpty){
                        Log.d("reach","Don't have a Menu")
                    } else{
                        appeList.clear()
                        for(document in task.result!!){
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

                                ))
                        }
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    println("failed")
                }
            }

    }


}
