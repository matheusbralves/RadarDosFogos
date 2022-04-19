package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.graphics.Bitmap

class Fire(var nome : String, var numeroCC : String, var distrito : String, var data : String, var hora : String) {
    //var nome : String = ""
    //var numeroCC : String = ""
    //var distrito : String = ""
    //var data : String = ""
    //var hora : String = ""
    //lateinit var foto : Bitmap

    override fun toString(): String {
        return "Fire = " +
                "(nome='$nome'," +
                " numeroCC='$numeroCC'," +
                " distrito='$distrito'," +
                " data=$data," +
                " hora=$hora,"
    }
}