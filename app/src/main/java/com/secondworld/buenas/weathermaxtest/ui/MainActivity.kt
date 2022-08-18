package com.secondworld.buenas.weathermaxtest.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.secondworld.buenas.weathermaxtest.R
import com.secondworld.buenas.weathermaxtest.core.bases.BaseActivity
import com.secondworld.buenas.weathermaxtest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavView.setupWithNavController(findNavController(R.id.nav_host_fragment))
    }
}