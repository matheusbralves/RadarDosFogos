package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentDashboardBinding

private const val ARG_RADIUSLIST = "param1"
private lateinit var binding: FragmentDashboardBinding

class DashboardFragment : Fragment() {
    //Lista de fogos dentro de um certo raio
    //Passar a lista dentro da main activity dentro dos ()
    private var radiusList = ArrayList(radiusListMain)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            radiusList = it.getStringArrayList(ARG_RADIUSLIST)!!//Confirmar se ta funcionando isso
        }

        //Adicionar isso em todos os fragmentos pra ficar com o titulo certo na barra laranja
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Inicio"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(
            R.layout.fragment_dashboard, container, false
        )
        binding = FragmentDashboardBinding.bind(view)
        return binding.root
    }


    //Falta onStart com toda a lógica da Dashboard

    companion object {
        @JvmStatic fun newInstance(radiusList: ArrayList<String>) : DashboardFragment =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_RADIUSLIST, radiusList)
                }
            }
    }
}