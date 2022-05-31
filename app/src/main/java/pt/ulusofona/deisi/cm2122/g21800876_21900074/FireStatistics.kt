package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentDetailsBinding
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentFireStatisticsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FireStatistics.newInstance] factory method to
 * create an instance of this fragment.
 */
class FireStatistics : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding : FragmentFireStatisticsBinding
    private lateinit var viewModel: FireViewModelV2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onStart() {
        super.onStart()
        binding.teste1.text = resources.getString(R.string.active_fire) + viewModel.getActiveFire().size.toString()
        binding.teste2.text = resources.getString(R.string.distMTotalFire) + viewModel.getDistrictWithMostFires()
        binding.teste3.text = resources.getString(R.string.distMActiveFire) + viewModel.getDistrictWithMostActiveFires()
        binding.teste5.text = resources.getString(R.string.totalOperational) + viewModel.getTotalOperationals().toString()
        binding.teste4.text = resources.getString(R.string.totalVeiculos) + viewModel.getTotalVehicles().toString()
        binding.teste6.text = resources.getString(R.string.totalPlanes) + viewModel.getTotalPlanes().toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Adicionar isso em todos os fragmentos pra ficar com o titulo certo na barra laranja
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.stats)

        val view = inflater.inflate(
            R.layout.fragment_fire_statistics, container, false
        )
        binding = FragmentFireStatisticsBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FireViewModelV2::class.java)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FireStatistics.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FireStatistics().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}