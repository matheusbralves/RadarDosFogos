package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FireParcelable(
    var nome: String,
    var numeroCC: String,
    var distrito: String,
    var conselho: String,
    var frequesia: String,
    var data: String,
    var hora: String,
    var status: String,
    var foto: Uri
) : Parcelable