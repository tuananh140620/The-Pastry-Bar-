package com.example.mock.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.mock.R
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager

//import com.bluapp.kotlinview.R
import com.google.android.material.tabs.TabLayout

class OrderFragment : Fragment() {
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.order_tab_holder, container, false)
        tabLayout = view.findViewById(R.id.tabs) as TabLayout
        viewPager = view.findViewById(R.id.view_pager) as ViewPager
        viewPager!!.adapter = MyAdapter(fragmentManager)
        tabLayout!!.post(Runnable { tabLayout!!.setupWithViewPager(viewPager) })

        
        return view
    }
    private inner class MyAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val intItems = 4;

        override fun getItem(position: Int): Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = AppetizerFragment()
                1 -> fragment = MainFragment()
                2 -> fragment = DessertFragment()
                3 -> fragment = CheckoutFragment()
            }
            return fragment!!
        }

        override fun getCount(): Int {
            return intItems
        }

        override fun getPageTitle(position: Int): CharSequence? {
            when (position) {
                0 -> return "Appetizer"
                1 -> return "Main"
                2 -> return "Desserts & Drinks"
                3 -> return "Checkout"
            }
            return null
        }
    }
}
