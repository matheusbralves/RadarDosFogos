package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/***
 *
 * Repository serve para juntar as listas do DAO e da API
 * Implementa funções usadas em fragmentos
 * Caminho == Implmenta função aqui >> Cria a chamada no ViewModel >> Instancia a ViewModel no Fragmento
 *
 *
 * LOCAL == FireModelRoom do DAO
 *
 * REMOTE == FireRetrofit da API
 *
 * ****/

class FireRepository private constructor(private val context: Context,
                                         private val local: FireModel,
                                         private val remote: FireModel) {

    private var fires : List<FireParcelable> = emptyList()

    //Funções usadas na ViewModel

    fun getAllFires(onFinished: (List<FireParcelable>) -> Unit) {
        if(ConnectivityUtil.isOnline(context)) {
            remote.getAllFires { history ->
                local.deleteAllOperations { registers ->
                    local.insertFires(history + registers) {
                        fires = history + registers
                        onFinished(history + registers)
                    }
                }
            }
        } else {
            local.getAllFires{ history ->
                fires = history
                onFinished(history)
            }
        }
    }

    fun getAllRegistros(onFinished: (List<FireParcelable>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(fires)
        }
    }

    fun getAllFiresList() : List<FireParcelable>{
        return fires
    }

    //Função de LongClick pra apagar
    fun deleteFire(fire: FireParcelable, onSucess: (List<FireParcelable>) -> Unit) {
        val firesFiltered : MutableList<FireParcelable> = mutableListOf()

        for(fireInList in fires){
            if(fireInList.uuid != fire.uuid && fireInList.isRegistry == "true"){
                firesFiltered.add(fireInList)
            }
        }

        local.deleteAllOperations {
            local.insertFires(firesFiltered) {
                onSucess(firesFiltered)
            }
        }
    }

    //Funções para estatisticas
    fun getActiveFires() : List<FireParcelable>{
        return fires
    }

    fun getDistrictWithMostFires() : String {
        val firesByDistrict : MutableMap<String, Int> = mutableMapOf()
        var districtWithMostFiresName = "Distrito"
        var districtWithMostFiresValue = 0

        for (f in fires) {
            if(firesByDistrict.containsKey(f.distrito)) {
                firesByDistrict.put(f.distrito, firesByDistrict.getValue(f.distrito) + 1)
            } else {
                firesByDistrict.put(f.distrito, 1)
            }
        }

        for(key in firesByDistrict.keys) {
            if(firesByDistrict[key]!! > districtWithMostFiresValue) {
                districtWithMostFiresName = key
                districtWithMostFiresValue = firesByDistrict[key]!!
            }
        }

        return districtWithMostFiresName
    }

    fun getDistrictWithMostActiveFires() : String {
        val firesByDistrict : MutableMap<String, Int> = mutableMapOf()
        var districtWithMostFiresName = "Distrito"
        var districtWithMostFiresValue = 0

        for (f in fires) {
            if(firesByDistrict.containsKey(f.distrito) && f.status == "Em curso") {
                firesByDistrict.put(f.distrito, firesByDistrict.getValue(f.distrito) + 1)
            } else {
                firesByDistrict.put(f.distrito, 1)
            }
        }

        for(key in firesByDistrict.keys) {
            if(firesByDistrict[key]!! > districtWithMostFiresValue) {
                districtWithMostFiresName = key
                districtWithMostFiresValue = firesByDistrict[key]!!
            }
        }

        return districtWithMostFiresName
    }

    fun getTotalOperationals(): Int {
        val activeFires = fires
        var totalOperationals = 0

        for(fire in activeFires) {
            totalOperationals += fire.operationals.toInt()
        }

        return totalOperationals;
    }

    fun getTotalVehicles(): Int {
        val activeFires = fires
        var totalVehicle = 0

        for(fire in activeFires) {
            totalVehicle += fire.vehicles.toInt()
        }

        return totalVehicle;
    }

    fun getTotalPlanes(): Int {
        val activeFires = fires
        var totalPlanes = 0

        for(fire in activeFires) {
            totalPlanes += fire.planes.toInt()
        }

        return totalPlanes;
    }

    //Singleton
    companion object {

        private var instance: FireRepository? = null

        fun init(context: Context, local: FireModel, remote: FireModel) {
            synchronized(this) {
                if(instance == null) {
                    instance = FireRepository(context, local, remote)
                }
            }
        }

        fun getInstance(): FireRepository {
            return instance ?: throw IllegalStateException("Repository not initialized")
        }

    }
}