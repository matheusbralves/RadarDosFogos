package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private val TAG = MainActivity::class.java.simpleName

//Variáveis para passar entre ecrãs tem que estar nessa activity
val radiusListMain = arrayListOf<String>()
val myRegisterList = arrayListOf<String>()

class MainActivity : AppCompatActivity() {

    val handler = Handler(Looper.getMainLooper())

    val looper = object: Runnable {
        override fun run() {
            binding.riskCircle.setBackgroundResource(R.drawable.circle_moderado)
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