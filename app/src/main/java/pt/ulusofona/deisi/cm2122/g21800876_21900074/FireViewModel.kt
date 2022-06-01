package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FireViewModel(application: Application) : AndroidViewModel(application) {

    private val model = FireRepository.getInstance()

    fun getAllFires(callback: (List<FireParcelable>) -> Unit){
        CoroutineScope(Dispatchers.Main).launch {
            model.getAllRegistrosDao(callback)
            //ALTERAR APENAS ESSA FUNÇÃO PARA MOSTRAR OS ITENS DA LISTA DA API
            //model.getAllRegistros(callback)
        }
    }

    fun onGetListDisplay(onFinished: (List<FireParcelable>) -> Unit){
        model.getAllRegistros(onFinished)
    }

    //Funções do ViewModel antigo talvez tenha q adaptar

    fun getActiveFire() : List<FireParcelable> {
        return model.getActiveFires()
    }

    fun getDistrictWithMostFires() : String {
        return model.getDistrictWithMostFires()
    }

    fun getDistrictWithMostActiveFires() : String {
        return model.getDistrictWithMostActiveFires()
    }

    fun getTotalOperationals(): Int {
        return model.getTotalOperationals()
    }

    fun getTotalVehicles(): Int {
        return model.getTotalVehicles()
    }

    fun getTotalPlanes(): Int {
        return model.getTotalPlanes()
    }

}