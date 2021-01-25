package com.creativedrewy.restaurantfinder.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.creativedrewy.restaurantfinder.R
import com.creativedrewy.restaurantfinder.fragment.RestaurantListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, RestaurantListFragment.newInstance())
                .commitNow()
        }
    }
}