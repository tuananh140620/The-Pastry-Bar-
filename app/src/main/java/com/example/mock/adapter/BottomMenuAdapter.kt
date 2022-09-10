package com.example.mock.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mock.fragments.*

class BottomMenuAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> OrderFragment()
            2 -> ServiceFragment()
            3 -> AccessoryFragment()

            else -> HomeFragment()
        }
    }



    override fun getCount(): Int {
        return 4
    }
}