package pt.ulusofona.deisi.cm2122.g21800876_21900074

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object NavigationManager{
    private fun placeFragment(fm: FragmentManager, fragment: Fragment){
        val transition = fm.beginTransaction()
        transition.replace(R.id.frame, fragment)
        transition.addToBackStack(null)
        transition.commit()
    }

    //Criar as funções de navegação

    fun goToDashboardFragment(fm: FragmentManager){
        placeFragment(fm, DashboardFragment())
    }

    fun goToRegisterFireFragment(fm: FragmentManager){
        placeFragment(fm, RegisterFireFragment())
    }
}