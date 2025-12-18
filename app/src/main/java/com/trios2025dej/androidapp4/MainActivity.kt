package com.trios2025dej.androidapp4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trios2025dej.androidapp4.fragments.CategoriesFragment
import com.trios2025dej.androidapp4.fragments.HadithListFragment
import com.trios2025dej.androidapp4.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_categories -> loadFragment(CategoriesFragment())
                R.id.nav_favorites -> loadFragment(HadithListFragment.newInstance("Faith"))
            }
            true
        }

        loadFragment(HomeFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
