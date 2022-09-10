package com.example.mock.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.example.mock.R
import com.example.mock.entity.Accessory
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.fragment_accessory.*

class AccessoryFragment : Fragment() {

    lateinit var db: FirebaseFirestore
    private var resList:ArrayList<Accessory> = ArrayList()
    var resArray:ArrayList<String> = ArrayList()
    lateinit var spinnerAdapter:ArrayAdapter<String>
    private lateinit var mapIntent:Intent



    override fun onCreate(savedInstanceState: Bundle?) {
        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            //.setTimestampsInSnapshotsEnabled(true)
            .build()
        db.firestoreSettings = settings
        getResList()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accessory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.google.com/maps/search/?api=1&query=38.651481,-90.338292"))

        screenshot_map.setOnClickListener() {

            startActivity(mapIntent)
        }





        spinnerAdapter =
            activity?.let { ArrayAdapter(it, R.layout.support_simple_spinner_dropdown_item, resArray) }!!
        switch_res_spinner.adapter = spinnerAdapter
        switch_res_spinner.setSelection(0)
        switch_res_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                switch_res_spinner.setSelection(0)
            }

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                title.text = resList[position].branchName
                address.text = resList[position].address
                open_time.text = resList[position].businessTime
                contact.text = resList[position].cell


                if (position == 0) {
                    mapIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/?api=1&query=21.01093977370695,105.79947760168453"))
                    screenshot_map.setImageResource(R.drawable.map1)

                }
                else{
                    mapIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/search/?api=1&query=10.770655162132867,106.69878054339806"))
                        screenshot_map.setImageResource(R.drawable.map2)
                }

            }
        }
    }

    private fun getResList(){
        db.collection("Restaurant")
            .get()
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    if(task.result!!.isEmpty){
                        Log.d("reach","Don't have history")
                    } else{

                        resList.clear()
                        resArray.clear()
                        for(document in task.result!!){
                            resList.add(
                                Accessory(
                                    document.get("location_description").toString(),
                                    document.get("cell").toString(),
                                    document.get("address").toString(),
                                    document.get("bussiness_time").toString()
                                )
                            )
                        }

                        for (item in resList){
                            resArray.add(item.branchName)
                        }
                        spinnerAdapter.notifyDataSetChanged()
                    }
                } else {
                    println("failed")
                }
            }
            .addOnFailureListener {

            }
    }





}
