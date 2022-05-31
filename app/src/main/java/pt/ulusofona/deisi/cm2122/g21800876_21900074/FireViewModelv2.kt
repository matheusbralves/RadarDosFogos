package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FireViewModel(application: Application) : AndroidViewModel(application) {

    private val model = FireModel(
        FireDatabase.getInstance(application).fireDao()
    )

    fun getAllFires(callback: (List<FireParcelable>) -> Unit){
        CoroutineScope(Dispatchers.Main).launch {
            model.getAllRegistrosDao(callback)
        }
    }

}