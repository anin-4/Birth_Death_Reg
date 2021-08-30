package com.example.birthdeathreg

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
       return when(position){
            1->address_detail()
            2->other_details()
            3->attach_documents()
           0->birth_details()
           else->birth_details()
        }

    }

    override fun getItemCount(): Int {
        return 4
    }
}