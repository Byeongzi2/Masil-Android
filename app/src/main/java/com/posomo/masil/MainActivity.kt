package com.posomo.masil

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.location.LocationServices
import com.posomo.masil.databinding.ActivityMainBinding
import com.posomo.masil.domain.model.Location
import com.posomo.masil.feature.map.BarMapFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private val locationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val isFineGranted = permissions.getOrDefault(FINE_LOCATION, false)
            val isCoarseGranted = permissions.getOrDefault(COARSE_LOCATION, false)
            if (isFineGranted || isCoarseGranted) {
                getLocationClient()
            } else {
                getLocationClient(false)
            }
        }

    private var userLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        getLocationClient()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        binding.bottomNav.setOnItemSelectedListener {
            navController.popBackStack()
            when (it.itemId) {
                R.id.mapFragment -> {
                    navController.navigate(
                        it.itemId, BarMapFragment.getBundle(userLocation)
                    )
                    true
                }
                else -> {
                    navController.navigate(it.itemId)
                    true
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getLocationClient(isGranted: Boolean = true) {
        if (isGranted) {
            if (checkPermission(FINE_LOCATION) || checkPermission(COARSE_LOCATION)) {
                val locationClient = LocationServices.getFusedLocationProviderClient(this)
                locationClient.lastLocation.run {
                    addOnSuccessListener { location ->
                        location?.let {
                            userLocation = Location(latitude = it.latitude, longitude = it.longitude)
                        }
                    }
                    addOnFailureListener {
                        userLocation = null
                    }
                }
                return
            }
        }
        locationPermissionLauncher.launch(PERMISSIONS)
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        private const val FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        private const val COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
        private val PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }
}