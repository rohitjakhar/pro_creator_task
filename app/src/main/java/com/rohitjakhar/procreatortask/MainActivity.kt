package com.rohitjakhar.procreatortask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.rohitjakhar.procreatortask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpNavController()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.nav_home -> {
                    showBottomBar()
                }
                R.id.nav_hall -> {
                    showBottomBar()
                }
                R.id.nav_ticket -> {
                    showBottomBar()
                }
                else -> {
                    hideBottomBar()
                }
            }
        }
    }

    private fun showBottomBar() {
        binding.bottomNavBar.show()
    }

    private fun hideBottomBar() {
        binding.bottomNavBar.hide()
    }

    private fun setUpNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_home) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_hall,
                R.id.nav_ticket
            )
        )
        binding.bottomNavBar.setupWithNavController(navController)
    }
}
