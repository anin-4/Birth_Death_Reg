package com.example.birthdeathreg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.birthdeathreg.network.DataClassHolder
import com.example.birthdeathreg.network.PostDataBody
import com.example.birthdeathreg.network.RetrofitInstance
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.HttpException
import java.net.HttpURLConnection

class MainActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar:ActionBar?=supportActionBar
        if(actionBar!=null){
            actionBar.title="Birth Details"
        }

        val tabLayout=findViewById<TabLayout>(R.id.topNavigation)
        val viewPager2=findViewById<ViewPager2>(R.id.view_pager2)
        val fm:FragmentManager= supportFragmentManager
        val fragmentAdapter=FragmentAdapter(fm,lifecycle)
        viewPager2.adapter=fragmentAdapter

        TabLayoutMediator(tabLayout,viewPager2){tab,position->
            when(position){
                0->{
                    tab.text="BIRTH DETAILS"
                }
                1->{
                    tab.text="ADDRESS DETAILS"
                }
                2->{
                    tab.text="OTHER DETAILS"
                }
                3 ->{
                    tab.text="ATTACH DOCUMENTS"
                }

            }
        }.attach()
    }
}