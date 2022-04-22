package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.net.Uri

data class Fire(
    var nome: String,
    var numeroCC: String,
    var distrito: String,
    var conselho: String,
    var frequesia: String,
    var data: String,
    var hora: String,
    var status: String,
    var foto: Uri?
) {

    override fun toString(): String {
        return "Fire(nome='$nome', " +
                "numeroCC='$numeroCC', " +
                "distrito='$distrito', " +
                "data='$data', " +
                "hora='$hora', " +
                "conselho='$conselho', " +
                "frequesia='$frequesia', " +
                "status='$status', " +
                "foto='$foto')"
    }

}