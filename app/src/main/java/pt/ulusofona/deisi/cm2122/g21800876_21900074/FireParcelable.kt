package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/***
 *
 * Classe padrão de Fire
 *
 * ***/

@Parcelize
data class FireParcelable(
    var uuid: String,
    var fire_name: String,
    var nome: String,
    var numeroCC: String,
    var distrito: String,
    var conselho: String,
    var frequesia: String,
    var data: String,
    var hora: String,
    var status: String,
    var foto: String,
    var distancia : String,
    var operationals : String,
    var vehicles : String,
    var planes : String,
    var lat: Double,
    var lng: Double,
    var isRegistry : String = "false",
    var photo : String = "2131230896"
) : Parcelable