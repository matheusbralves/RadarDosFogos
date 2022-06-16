package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/***
 *
 * Classe que serve de conexão entre Repository e Fragmentos
 * Chamadas das funções do reposotory
 *
 * ***/

class FireViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FireRepository.getInstance()

    fun getAllFires(callback: (List<FireParcelable>) -> Unit){
        CoroutineScope(Dispatchers.Main).launch {
            repository.getAllFires(callback)
        }
    }

    fun getAllRegistros(onFinished: (List<FireParcelable>) -> Unit){
        repository.getAllRegistros(onFinished)
    }

    fun getAllFiresList(): List<FireParcelable> {
        return repository.getAllFiresList()
    }

    fun getActiveFire() : List<FireParcelable> {
        return repository.getActiveFires()
    }

    fun getDistrictWithMostFires() : String {
        return repository.getDistrictWithMostFires()
    }

    fun getDistrictWithMostActiveFires() : String {
        return repository.getDistrictWithMostActiveFires()
    }

    fun getTotalOperationals(): Int {
        return repository.getTotalOperationals()
    }

    fun getTotalVehicles(): Int {
        return repository.getTotalVehicles()
    }

    fun getTotalPlanes(): Int {
        return repository.getTotalPlanes()
    }

    //Função pra deletar em longClick
    fun deleteFire(fire: FireParcelable, onSucess: () -> Unit) {
        return repository.deleteFire(fire, onSucess)
    }
}