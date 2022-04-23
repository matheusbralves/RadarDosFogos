package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentDashboardBinding

private lateinit var binding: FragmentDashboardBinding
private val TAG = MainActivity::class.java.simpleName

class DashboardFragment : Fragment() {
    private lateinit var viewModel : FireViewModel
    private val adapter = FireListAdapter(onClick = ::onItemClick)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Adicionar isso em todos os fragmentos pra ficar com o titulo certo na barra laranja
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.inicio)

        val view = inflater.inflate(
            R.layout.fragment_dashboard, container, false
        )
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        binding = FragmentDashboardBinding.bind(view)
        return binding.root
    }

    override fun onStart(){
        super.onStart()
        //Log.i(TAG, "Tela dashboard")
        radiusSpinnerSetup()
        binding.radiusList.layoutManager = LinearLayoutManager(activity as Context)
        binding.radiusList.adapter = adapter
        binding.callHelp.setOnClickListener{ callHelp() }
        viewModel.getDashboardRegistros25 { updateList(it) }
    }

    private fun onItemClick(operation: FireParcelable) {
        NavigationManager.goToDetaisFragment(parentFragmentManager, operation)
    }

    private fun updateList(fireList : List<FireParcelable>){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.updateItems(fireList)
        }
    }

    private fun radiusSpinnerSetup(){
        val spinner: Spinner = binding.spinner
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.radius_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                radiusSelect()
            }
        }
    }

    private fun radiusSelect(){
        when (binding.spinner.selectedItem.toString()) {
            "25 KM" -> {
                viewModel.getDashboardRegistros25 { updateList(it) }
            }

            "35 KM" -> {
                viewModel.getDashboardRegistros35 { updateList(it) }
            }

            "50 KM" -> {
                viewModel.getDashboardRegistros50 { updateList(it) }
            }

            else -> print("nada")
        }
    }

    private fun callHelp(){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:112")
        startActivity(intent)
    }
}