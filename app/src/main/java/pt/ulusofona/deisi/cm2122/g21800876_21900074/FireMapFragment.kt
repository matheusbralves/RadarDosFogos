package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pt.ulusofona.deisi.cm2122.g21800876_21900074.databinding.FragmentFireMapBinding
import java.util.*

class FireMapFragment : Fragment() , OnLocationChangedListener {

    private lateinit var binding: FragmentFireMapBinding
    private lateinit var geocoder: Geocoder
    private var map: GoogleMap? = null

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
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        binding.listBtn.setOnClickListener{ activity?.let { it1 ->
            NavigationManager.goToFireListFragment(
                it1.supportFragmentManager)
        } }
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
            .zoom(12f)
            .build()
        map?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        val sydney = LatLng(latitude, longitude)
        map?.addMarker(
            MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney")
        )
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