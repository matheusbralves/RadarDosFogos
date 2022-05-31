/*
package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.google.android.gms.location.*

@SuppressLint("MissingPermission")
class FusedLocation private constructor(context: Context) : LocationCallback{

    private val TAG = FusedLocation::class.java.simpleName

    private val UPDATE_INTERVAL = 20 * 1000L

    private var client = FusedLocationProviderClient(context)

    private var locationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        interval = UPDATE_INTERVAL
    }

    init {
        val locationSettingsRequest = LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()

        LocationServices.getSettingsClient(context)
            .checkLocationSettings(locationSettingsRequest)

        client.requestLocationUpdates(locationRequest, this, Looper.getMainLooper())
    }
}
*/