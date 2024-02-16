package com.radea.alquranku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation
import com.radea.alquranku.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationItems = mutableListOf(
            CurvedBottomNavigation.Model(R.id.homeFragment, getString(R.string.home), R.drawable.ic_home_black_24dp),
            CurvedBottomNavigation.Model(R.id.scheduleSholatFragment,
                getString(R.string.value_jadwal), R.drawable.baseline_date_range_24)
        )

        binding.bottomNavigation.apply {
            bottomNavigationItems.forEach { add(it) }
            setOnClickMenuListener {
                navController.navigate(it.id)
            }
            show(R.id.homeFragment)
        }
    }

}