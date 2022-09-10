package com.example.mock.fragments

//import com.google.firebase.firestore.auth.User
import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mock.R
import com.example.mock.entity.User
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.register_tab_fragment.*
import java.util.*


class SignUpFragment: Fragment(){
    private val mAuth = FirebaseAuth.getInstance()
    private lateinit var db : FirebaseFirestore
    private var hide = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.register_tab_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            //.setTimestampsInSnapshotsEnabled(true)
            .build()
        db.firestoreSettings = settings

        registerButton.setOnClickListener{
            startRegistering()
        }

        toggle.setOnClickListener{
            if (hide){
                password_R.transformationMethod = null
                hide = false
                toggle.setBackgroundResource(R.drawable.hide)
            }
            else{
                password_R.transformationMethod= PasswordTransformationMethod()
                hide = true
                toggle.setBackgroundResource(R.drawable.show)
            }
            Log.d("mylog", "here")

        }

    }


    private fun startRegistering(){
        val emailIn = email_R.text.toString().trim()
        val passwordIn = password_R.text.toString().trim()


        if (emailIn.isEmpty()){
            val myToast = Toast.makeText(activity,"Please Enter a valid Email Address!", Toast.LENGTH_LONG)
            myToast.show()
        }
        else if(passwordIn.isEmpty()){
            val myToast = Toast.makeText(activity,"Please Enter a valid Password!", Toast.LENGTH_LONG)
            myToast.show()
        }
        else {

            mAuth.createUserWithEmailAndPassword(emailIn, passwordIn)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")

                        val myToast = Toast.makeText(activity,"createUserWithEmail:success", Toast.LENGTH_LONG)
                        myToast.show()
                        val user = mAuth.currentUser
                        saveUserToDatabase()


                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d("mylog", "createUserWithEmail:failure", task.exception)
                        val exception = task.exception
                        val myToast = Toast.makeText(activity,"createUserWithEmail:failure $exception", Toast.LENGTH_LONG)
                        myToast.show()

                    }

                    // ...

                }

        }
    }

    private fun saveUserToDatabase(){
    val uid =  mAuth.uid?:""
    if(username_R.text.toString()!=""){
        val user = User(uid,username_R.text.toString(),1000)

        val userMap : MutableMap<String,Any> = HashMap()
        userMap["uid"] = user.uid
        userMap["username"] = user.username
        userMap["chipAmount"] = user.chipAmount
        db.collection("users")
            .add(userMap)
            .addOnSuccessListener(OnSuccessListener<DocumentReference>{
                val myToast = Toast.makeText(this.context,"User created",Toast.LENGTH_LONG)
                myToast.show()
            })
            .addOnFailureListener {
                val myToast = Toast.makeText(this.context,"Failed",Toast.LENGTH_LONG)
                myToast.show()
            }
    }else{
        val myToast = Toast.makeText(this.context,"Value cannot be null!",Toast.LENGTH_LONG)
        myToast.show()
    }

    }
}

