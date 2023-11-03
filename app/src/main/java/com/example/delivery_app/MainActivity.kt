package com.example.delivery_app

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.delivery_app.databinding.ActionBarBinding
import com.example.delivery_app.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setCustomView(R.layout.action_bar)

        val actionBarBinding = supportActionBar?.customView?.let { ActionBarBinding.bind(it) }

        actionBarBinding?.llCity?.setOnClickListener {
            // some onClick handler
            Log.d(TAG, "City clicked")
        }

        actionBarBinding?.ivQr?.setOnClickListener {
            // some onClick handler
            Log.d(TAG, "QR clicked")
        }

        navView.setupWithNavController(navController)
    }
}