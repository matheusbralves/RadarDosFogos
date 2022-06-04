package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentFireListBinding


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
        districtSpinnerSetup()
        binding.fogosActivosCount.text = viewModel.getActiveFire().size.toString()
        binding.fogosActivosCount.text = viewModel.getDistrictWithMostFires()
        viewModel.getAllFires { updateList(it) }
    }

    private fun updateList(fireList : List<FireParcelable>){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.updateItems(fireList)
        }
    }

    private fun onItemClick(fire: FireParcelable) {
        NavigationManager.goToDetaisFragment(parentFragmentManager, fire)
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

    private fun districtSpinnerSetup(){
        val spinner: Spinner = binding.spinner

        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                updateList(getFiresByDistrict(spinner.selectedItem.toString()))
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                return
            }
        })

        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.district_array_plus_all,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun getFiresByDistrict(district:String): MutableList<FireParcelable> {
        if(district == "Todos os Distritos") {
            return viewModel.getAllFiresList() as MutableList<FireParcelable>
        }

        val allFires = viewModel.getAllFiresList()
        val chosenFires = mutableListOf<FireParcelable>()
        for(fire in allFires) {
            if(fire.distrito == district) {
                chosenFires.add(fire)
            }
        }

        return chosenFires
    }
}