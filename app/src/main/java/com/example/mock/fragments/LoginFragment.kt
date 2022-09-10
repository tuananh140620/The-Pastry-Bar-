package com.example.mock.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mock.R
import com.example.mock.adapter.HomeActivity
import com.example.mock.viewmodel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.login_tab_fragment.*

class LoginFragment : Fragment() {

    lateinit var db: FirebaseFirestore
    private lateinit var viewModel: LoginViewModel
    private var username: String? = ""
    private lateinit var googleSignInClient: GoogleSignInClient
    val mAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_tab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this.requireContext(), gso)

    }


    override fun onStart() {
        super.onStart()
        submit.setOnClickListener() {
            login()
        }

        signUp.setOnClickListener() {
            val fragment = SignUpFragment()
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        google_sign_in_button.setOnClickListener {
            //googleLogin()
        }

    }


    private fun shouldStartSignIn(): Boolean {
        return (!viewModel.isSigningIn) && (FirebaseAuth.getInstance().currentUser == null)
    }


    private fun login() {

        val emailIn = email.text.toString().trim()
        val passwordIn = password.text.toString().trim()
        mAuth.signInWithEmailAndPassword(emailIn, passwordIn)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("mylog", "signInWithEmail:success")
                    val user = mAuth.currentUser
                    val email = user!!.displayName
                    val uid = user!!.uid
                    Log.d("mylog", emailIn)
                    val intent = Intent(this.context, HomeActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Log.d("mylog", "signInWithEmail:failure", task.exception)
                    val exception = task.exception
                    val myToast = Toast.makeText(
                        activity,
                        "signInWithEmail:failure $exception",
                        Toast.LENGTH_LONG
                    )
                    myToast.show()


                }


            }

    }


    companion object {

        private const val TAG = "MainActivity"

        private const val RC_SIGN_IN = 9001

        private const val LIMIT = 50
    }

}