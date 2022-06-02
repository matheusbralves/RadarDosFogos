package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentFireMapBinding
import java.util.*

class FireMapFragment : Fragment(), OnLocationChangedListener {

    private lateinit var binding: FragmentFireMapBinding
    private lateinit var geocoder: Geocoder
    private var map: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_fire_map, container, false)
        geocoder = Geocoder(context, Locale.getDefault())
        binding = FragmentFireMapBinding.bind(view)
        binding.map.onCreate(savedInstanceState)
        binding.map.getMapAsync {
            map = it
            FusedLocation.registerListener(this)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.map.onResume()
    }

    override fun onLocationChanged(latitude: Double, longitude: Double) {
        placeCamera(latitude, longitude)
        placeCityName(latitude, longitude)
    }

    private fun placeCamera(latitude: Double, longitude: Double) {
        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(latitude, longitude))
            .zoom(0f)
            .build()
        map?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    private fun placeCityName(latitude: Double, longitude: Double) {
        val addresses = geocoder.getFromLocation(latitude, longitude, 5)
        val location = addresses.first { it.locality != null && it.locality.isNotEmpty() }
        binding.tvCityName.text = location.locality
    }

    override fun onDestroy() {
        super.onDestroy()
        FusedLocation.unregisterListener(this)
    }

}