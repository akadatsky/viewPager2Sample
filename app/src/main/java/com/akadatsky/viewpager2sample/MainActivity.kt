package com.akadatsky.viewpager2sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager2
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        bottomNavigation = findViewById(R.id.bottomNavigation)

        viewPager.adapter = SampleAdapter(this)

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bottomNavigation.selectedItemId = when(position) {
                    0 -> R.id.page_1
                    1 -> R.id.page_2
                    else -> throw IllegalArgumentException("Only 2 tabs")
                }
            }
        })

        bottomNavigation.setOnItemSelectedListener {
            return@setOnItemSelectedListener when (it.itemId) {
                R.id.page_1 -> {
                    viewPager.currentItem = 0
                    true
                }
                R.id.page_2 -> {
                    viewPager.currentItem = 1
                    true
                }
                else -> false
            }
        }

    }
}

class SampleAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
      return when(position) {
          0 -> Tab1Fragment()
          1 -> Tab2Fragment()
          else -> throw IllegalArgumentException("Only 2 tabs")
      }
    }

}