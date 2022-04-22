package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.net.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//private val TAG = MainActivity::class.java.simpleName

object FireModel {

    private val registros = mutableListOf(
        FireParcelable("test 1", "11111111", "Setubal","", "",
        "22/04/2022", "14:15", "Em curso", Uri.parse(R.drawable.sem_foto.toString())),

        FireParcelable("test 2", "22222222", "Lisboa","", "",
        "22/04/2022", "12:10", "Terminado", Uri.parse(R.drawable.sem_foto.toString())),

        FireParcelable("test 3", "33333333", "Setubal","", "",
        "22/04/2022", "10:25", "Em curso", Uri.parse(R.drawable.sem_foto.toString())),
    )

    fun addRegistro(nome : String,
                    numeroCC : String,
                    distrito : String,
                    conselho : String,
                    frequesia : String,
                    data : String,
                    hora : String,
                    status : String,
                    foto : Uri?
    )

    {
        foto?.let {
            FireParcelable(nome, numeroCC, distrito, conselho, frequesia, data, hora, status,
                it
            )
        }?.let { registros.add(it) }
    }

    fun getAllRegistros(onFinished: (List<FireParcelable>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(registros)
        }
    }
}