package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.fondesa.kpermissions.extension.permissionsBuilder
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.ActivityMainBinding
import java.util.jar.Manifest
import com.fondesa.kpermissions.allGranted
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.extension.send

private lateinit var binding: ActivityMainBinding
private val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())

    private val cores = arrayListOf(
        R.drawable.circle_maximo,
        R.drawable.circle_muito_elevado,
        R.drawable.circle_elevado,
        R.drawable.circle_moderado,
        R.drawable.circle_reduzido
    )

    var indexCores = -1

    private val looper = object: Runnable {
        override fun run() {
            indexCores += 1
            if(indexCores > 4){
                indexCores = 0
            }
            binding.riskCircle.setBackgroundResource(cores[indexCores])
            handler.postDelayed(this, 20000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(!screenRotated(savedInstanceState)){
            NavigationManager.goToDashboardFragment(supportFragmentManager)
        }

        permissionsBuilder(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION).build().send { result ->
            if (result.allGranted()) {
                if(!screenRotated(savedInstanceState)) {
                    NavigationManager.goToDashboardFragment(
                        supportFragmentManager
                    )
                } else {
                    finish()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        setSupportActionBar(binding.toolbar)
        setupDrawerMenu()
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(looper, 20000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(looper)
    }

    private fun screenRotated(savedInstanceState: Bundle?) : Boolean {
        return savedInstanceState != null
    }

    private fun setupDrawerMenu() {
        val toggle = ActionBarDrawerToggle(this,
            binding.drawer, binding.toolbar,
            R.string.drawer_open, R.string.drawer_close
        )
        binding.navDrawer.setNavigationItemSelectedListener {
            onClickNavigationItem(it)
        }
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun onClickNavigationItem(item: MenuItem) : Boolean {
        when(item.itemId){
            //Adicionar para mais navegações
            R.id.nav_inicio ->
                NavigationManager.goToDashboardFragment(
                    supportFragmentManager
                )

            R.id.nav_registrar ->
                NavigationManager.goToRegisterFireFragment(
                    supportFragmentManager
                )

            R.id.nav_lista ->
                NavigationManager.goToFireListFragment(
                    supportFragmentManager
                )

            R.id.nav_mapa ->
                NavigationManager.goToFireMapFragment(
                    supportFragmentManager
                )

            R.id.nav_stats ->
                NavigationManager.goToFireStatistics(
                    supportFragmentManager
                )
        }
        binding.drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(binding.drawer.isDrawerOpen(GravityCompat.START)){
            binding.drawer.closeDrawer(GravityCompat.START)
        } else if (supportFragmentManager.backStackEntryCount == 1){
            finish()
        } else {
            super.onBackPressed()
        }
    }
}