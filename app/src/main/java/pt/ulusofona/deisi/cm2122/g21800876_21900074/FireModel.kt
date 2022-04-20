package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.graphics.Bitmap
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//private val TAG = MainActivity::class.java.simpleName

object FireModel {

    private val registros = arrayListOf(
        Fire("turminha do siri", "12345678", "Setubal","12323", "1234"),
        Fire("hdhasodkhaso", "12345678", "Setubal","12323", "1234"),
        Fire("teste", "12345678", "Setubal","12323", "1234")
    )

    fun addRegistro(nome : String, numeroCC : String, distrito : String, data : String,
    hora : String) { registros.add(Fire(nome, numeroCC, distrito, data, hora)) }

    fun getAllRegistros(onFinished : (List<Fire>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(registros)
        }
    }
}