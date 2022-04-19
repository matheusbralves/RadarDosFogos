package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.graphics.Bitmap
import android.util.Log

private val TAG = MainActivity::class.java.simpleName

class FireModel {
    private val registros = arrayListOf<Fire>()

    fun addRegistro(nome : String, numeroCC : String, distrito : String, data : String,
    hora : String, foto : Bitmap) {
        val fire = Fire()
        fire.nome = nome
        fire.numeroCC = numeroCC
        fire.distrito = distrito
        fire.data = data
        fire.hora = hora
        fire.foto = foto

        registros.add(fire)
        Log.i(TAG, "$fire")
    }

    fun getAllRegistros() : List<Fire> {
        return registros
    }
}