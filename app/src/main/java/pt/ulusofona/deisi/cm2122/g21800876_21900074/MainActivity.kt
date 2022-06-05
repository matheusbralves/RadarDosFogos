package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.fondesa.kpermissions.extension.permissionsBuilder
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.ActivityMainBinding
import java.util.jar.Manifest
import com.fondesa.kpermissions.allGranted
import com.fondesa.kpermissions.extension.permissionsBuilder
import com.fondesa.kpermissions.extension.send

private lateinit var binding: ActivityMainBinding
private val TAG = MainActivity::class.java.simpleName

class MainActivity : AppCompatActivity(), OnLocationChangedListener {

    private lateinit var viewModel : FireViewModel


    override fun onLocationChanged(latitude: Double, longitude: Double) {
        val fires = viewModel.getAllFiresList()
        var nFires = 0
        for(fire in fires) {
            if(defineDistance(latitude,longitude,fire.lat, fire.lng) <= 150.00){
                nFires++
            }
        }

        when(nFires) {
            0 -> binding.riskCircle.setBackgroundResource(R.drawable.circle_reduzido)
            1 -> binding.riskCircle.setBackgroundResource(R.drawable.circle_moderado)
            2 -> binding.riskCircle.setBackgroundResource(R.drawable.circle_elevado)
            3 -> binding.riskCircle.setBackgroundResource(R.drawable.circle_muito_elevado)
            4 -> binding.riskCircle.setBackgroundResource(R.drawable.circle_maximo)
        }
    }

    fun defineDistance(userLat:Double, userLng:Double, fireLat:Double, fireLng:Double):Double {
        var earthRadiusKm = 6371;

        var dLat = degreesToRadians(userLat-fireLat);
        var dLon = degreesToRadians(userLng-fireLng);

        var convertedUserLat = degreesToRadians(userLat);
        var convertedFireLat = degreesToRadians(fireLat);

        var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(convertedUserLat) * Math.cos(convertedFireLat);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadiusKm * c;
    }

    fun degreesToRadians(degrees:Double):Double {
        return degrees * Math.PI / 180;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        FusedLocation.registerListener(this)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
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