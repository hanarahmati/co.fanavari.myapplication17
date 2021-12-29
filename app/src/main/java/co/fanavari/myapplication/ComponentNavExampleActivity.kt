package co.fanavari.myapplication

import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import co.fanavari.myapplication.databinding.ActivityComponentNavExampleBinding

class ComponentNavExampleActivity : AppCompatActivity() {
    private lateinit var binding:ActivityComponentNavExampleBinding

    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navHostFragment: NavHostFragment

    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComponentNavExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)

        navHostFragment = supportFragmentManager.
        findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        drawerLayout = binding.drawerLayout

        binding.navigationView.setupWithNavController(navController)
        binding.bottomNavView.setupWithNavController(navController)

        appBarConfig = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfig)

        listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->
            if(destination.id == R.id.firstFragment){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.green_500)))
                    binding.bottomNavView.setBackgroundColor(getColor(R.color.green_500))
                }
            } else if(destination.id == R.id.secondFragment){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.purple_500)))
                    binding.bottomNavView.setBackgroundColor(getColor(R.color.purple_500))
                }}
        }
    }

    override fun onPause() {
        super.onPause()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainerView)
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }
}

