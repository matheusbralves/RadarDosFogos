package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.graphics.Bitmap
import android.util.Log

private val TAG = MainActivity::class.java.simpleName

class FireModel {

    private val registros = arrayListOf<Fire>(Fire("turminha do siri", "12345678", "Setubal","12323", "1234"),
        Fire("hdhasodkhaso", "12345678", "Setubal","12323", "1234"),
        Fire("teste", "12345678", "Setubal","12323", "1234"))

    fun addRegistro(nome : String, numeroCC : String, distrito : String, data : String,
    hora : String) {

        registros.add(Fire(nome, numeroCC, distrito, data, hora))
        //Log.i(TAG, "$fire")
    }

    fun getAllRegistros() : List<Fire> {
        return registros
    }
}