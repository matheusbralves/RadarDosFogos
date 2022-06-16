package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentFireStatisticsBinding

/***
 *
 * Caso precise mudar algo nas Estatisticas(Extra) é nesse fragmento
 *
 * ***/


private lateinit var binding : FragmentFireStatisticsBinding

class FireStatistics : Fragment() {
    private lateinit var viewModel : FireViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Adicionar isso em todos os fragmentos pra ficar com o titulo certo na barra laranja
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.stats)

        //Layout das Estatisticas
        val view = inflater.inflate(
            R.layout.fragment_fire_statistics, container, false
        )
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        binding = FragmentFireStatisticsBinding.bind(view)
        return binding.root
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
}