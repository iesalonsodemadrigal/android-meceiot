package edu.iesam.meceiot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.iesam.meceiot.databinding.ActivityMainBinding
import edu.iesam.meceiot.features.login.data.local.SessionManager
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val sessionManager: SessionManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainMenu.setupWithNavController(navController)

        if (!sessionManager.isLoggedIn()) {
            navController.navigate(R.id.login_fragment)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.login_fragment -> findViewById<BottomNavigationView>(R.id.main_menu).visibility =
                    BottomNavigationView.GONE

                else -> findViewById<BottomNavigationView>(R.id.main_menu).visibility =
                    BottomNavigationView.VISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sessionManager.logout()
    }
}