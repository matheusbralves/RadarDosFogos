package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentFireMapBinding
import java.util.*


class FireMapFragment : Fragment() , OnLocationChangedListener {

    private lateinit var binding: FragmentFireMapBinding
    private lateinit var geocoder: Geocoder
    private lateinit var viewModel : FireViewModel
    private var fires: List<FireParcelable> = listOf()
    private var map: GoogleMap? = null
    private var didInitialSetup = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = getString(R.string.mapa_de_fogos)
        val view = inflater.inflate(R.layout.fragment_fire_map, container, false)
        geocoder = Geocoder(context, Locale.getDefault())
        binding = FragmentFireMapBinding.bind(view)
        binding.map.onCreate(savedInstanceState)
        binding.map.getMapAsync {
            map = it
            FusedLocation.registerListener(this)
        }
        viewModel = ViewModelProvider(this).get(FireViewModel::class.java)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.listBtn.setOnClickListener{ activity?.let { it1 ->
            NavigationManager.goToFireListFragment(
                it1.supportFragmentManager)
        } }
        fires = viewModel.getAllFiresList()
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onLocationChanged(latitude: Double, longitude: Double) {
        if(!didInitialSetup) {
            placeCamera(latitude, longitude)
            didInitialSetup = true
        }
        //placeCityName(latitude, longitude)
        placeMarkers()
    }

    fun placeMarkers() {
        for (fire in fires){
            map?.addMarker(
                MarkerOptions()
                    .position(LatLng(fire.lat, fire.lng))
                    .title(fire.uuid)
            )
        }

        map?.setOnMarkerClickListener { marker ->
            val markerName = marker.title
            if (markerName != null) {
                getFireById(markerName)?.let {
                    NavigationManager.goToDetaisFragment(
                        parentFragmentManager,
                        it
                    )
                }
            }
            false
        }
    }

    private fun placeCamera(latitude: Double, longitude: Double) {
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(latitude, longitude))
            .zoom(8f)
            .build()
        map?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private fun getFireById(id: String): FireParcelable? {
        for (fire in fires){
            if(fire.uuid == id){
                return fire
            }
        }
        return null
    }

    private fun placeCityName(latitude: Double, longitude: Double) {
        val addresses = geocoder.getFromLocation(latitude, longitude, 5)
        val location = addresses.first { it.locality != null && it.locality.isNotEmpty() }
    }

    override fun onDestroy() {
        super.onDestroy()
        FusedLocation.unregisterListener(this)
    }

}