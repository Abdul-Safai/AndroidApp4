package com.trios2025dej.androidapp4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.trios2025dej.androidapp4.fragments.CategoriesFragment
import com.trios2025dej.androidapp4.fragments.FavoritesFragment
import com.trios2025dej.androidapp4.fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Default fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, HomeFragment())
            .commit()

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    true
                }

                R.id.menu_categories -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CategoriesFragment())
                        .commit()
                    true
                }

                R.id.menu_favorites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, FavoritesFragment())
                        .commit()
                    true
                }

                else -> false
            }
        }
    }
}
