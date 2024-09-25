package com.carry.wanandroid.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.carry.wanandroid.ui.R
import com.carry.wanandroid.ui.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        NavigationUI.setupWithNavController(binding.bottomNav,getNavController())
    }
    private fun getNavController() = (supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment).navController
}