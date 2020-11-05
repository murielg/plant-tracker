package com.gonzoapps.planttracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.gonzoapps.planttracker.databinding.ActivityMainBinding
import com.gonzoapps.planttracker.db.PlantDatabase
import com.gonzoapps.planttracker.screens.myplants.PlantsViewModel
import com.gonzoapps.planttracker.screens.myplants.PlantsViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    private lateinit var sharedViewModel: PlantsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val application = requireNotNull(this).application

        val dataSource = PlantDatabase.getInstance(application).plantDatabaseDao

        val viewModelFactory = PlantsViewModelFactory(dataSource,application)

        sharedViewModel = ViewModelProvider(this,viewModelFactory).get(PlantsViewModel::class.java)

        drawerLayout = binding.drawerLayout

        val navController = this.findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(navController.graph)
        NavigationUI.setupActionBarWithNavController(this, navController)

        NavigationUI.setupWithNavController(binding.navView, navController)

        // prevent nav gesture if not on start destination
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, args: Bundle? ->
            if (nd.id == nc.graph.startDestination) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }
    // Adding UP button to the app bar
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
//        return NavigationUI.navigateUp(navController, drawerLayout)
        return NavigationUI.navigateUp(navController,appBarConfiguration)
    }
}