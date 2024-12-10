package edu.iesam.meceiot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.SkeletonLayout
import edu.iesam.meceiot.databinding.ActivityMainBinding
import edu.iesam.meceiot.features.externalresources.presentation.ExternalResourcesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var skeleton: Skeleton
    private val viewModel: ExternalResourcesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupObservers()
    }

    private fun setupView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val navController = navHostFragment.navController
        binding.mainMenu.setupWithNavController(navController)
        skeleton = binding.skeletonLayout as SkeletonLayout
    }

    private fun setupObservers() {
        viewModel.uiState.observe(this, Observer { uiState ->
            if (uiState.loading) {
                skeleton.showSkeleton()
            } else {
                skeleton.showOriginal()
            }
        })
    }
}