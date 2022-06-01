package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentFireListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FireListFragment : Fragment() {
    private lateinit var binding: FragmentFireListBinding
    private lateinit var viewModel : FireViewModel
    private val adapter = FireListAdapter(onClick = ::onItemClick)

    override fun onStart() {
        super.onStart()
        binding.rvList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvList.adapter = adapter
        binding.mapBtn.setOnClickListener{ activity?.let { it1 ->
            NavigationManager.goToFireMapFragment(
                it1.supportFragmentManager)
        } }
        binding.fogosActivosCount.text = viewModel.getActiveFire().size.toString()
        binding.fogosActivosCount.text = viewModel.getDistrictWithMostFires()
        viewModel.getAllFires { updateList(it) }
    }

    private fun updateList(fireList : List<FireParcelable>){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.updateItems(fireList)
        }
    }

    private fun onItemClick(operation: FireParcelable) {
        NavigationManager.goToDetaisFragment(parentFragmentManager, operation)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Adicionar isso em todos os fragmentos pra ficar com o titulo certo na barra laranja
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.lista_de_fogos)

        val view = inflater.inflate(
            R.layout.fragment_fire_list, container, false
        )
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        binding = FragmentFireListBinding.bind(view)
        return binding.root
    }
}