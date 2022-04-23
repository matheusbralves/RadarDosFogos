package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentFireListBinding
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentFireMapBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FireMapFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

private lateinit var binding : FragmentFireMapBinding

class FireMapFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        //Adicionar isso em todos os fragmentos pra ficar com o titulo certo na barra laranja
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.mapa_de_fogos)

        val view = inflater.inflate(
            R.layout.fragment_fire_map, container, false
        )

        binding = FragmentFireMapBinding.bind(view)

        // Inflate the layout for this fragment
        return  binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.listBtn.setOnClickListener{ activity?.let { it1 ->
            NavigationManager.goToFireListFragment(
                it1.supportFragmentManager)
        } }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FireMapFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FireMapFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}