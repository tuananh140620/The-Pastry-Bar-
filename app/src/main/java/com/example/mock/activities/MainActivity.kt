package com.example.mock.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mock.R
import com.example.mock.adapter.ViewPagerAdapter
import com.example.mock.fragments.LoginFragment
import com.example.mock.fragments.SignUpFragment
import kotlinx.android.synthetic.main.login_page_tab_holder.*


// This is the login page
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
