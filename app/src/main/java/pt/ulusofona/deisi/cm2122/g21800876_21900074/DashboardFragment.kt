package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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

class DashboardFragment : Fragment(), OnLocationChangedListener  {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel : FireViewModel
    private val adapter = FireListAdapter(onClick = ::onItemClick)
    private var userLat = 0.0;
    private var userLng = 0.0;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Adicionar isso em todos os fragmentos pra ficar com o titulo certo na barra laranja
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.inicio)

        val view = inflater.inflate(
            R.layout.fragment_dashboard, container, false
        )
        FusedLocation.registerListener(this)
        binding = FragmentDashboardBinding.bind(view)
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        return binding.root
    }

    override fun onStart(){
        super.onStart()
        radiusSpinnerSetup()
        binding.radiusList.layoutManager = LinearLayoutManager(activity as Context)
        binding.radiusList.adapter = adapter
        binding.callHelp.setOnClickListener{ callHelp() }
        viewModel.getAllFires { updateList(it) }
    }

    override fun onLocationChanged(latitude: Double, longitude: Double) {
        userLat = latitude
        userLng = longitude
    }

    private fun onItemClick(fire: FireParcelable) {
        NavigationManager.goToDetaisFragment(parentFragmentManager, fire)
    }

    private fun updateList(fireList : List<FireParcelable>){
        CoroutineScope(Dispatchers.Main).launch {
            adapter.updateItems(fireList)
        }
    }

    private fun radiusSpinnerSetup(){
        val spinner: Spinner = binding.spinner

        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                updateList(getFiresByMaxDistance(userLat,userLng,spinner.selectedItem.toString()))
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                return
            }
        })
        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.radius_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun getFiresByMaxDistance(userLat:Double, userLng:Double, maxDistance: String): MutableList<FireParcelable> {
        val allFires = viewModel.getAllFiresList()
        val chosenFires = mutableListOf<FireParcelable>()
        var distance = 0.0

        when(maxDistance) {
            "300 KM" -> distance = 300.00
            "150 KM" -> distance = 150.00
            "50 KM" -> distance = 50.00
        }

        for(fire in allFires) {
            if(defineDistance(userLat, userLng, fire.lat,fire.lng) <= distance) {
                chosenFires.add(fire)
            }
        }

        return chosenFires
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

    private fun callHelp(){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:112")
        startActivity(intent)
    }
}