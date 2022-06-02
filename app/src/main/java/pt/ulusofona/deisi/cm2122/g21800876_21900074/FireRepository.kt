package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FireRepository private constructor(private val context: Context,
                                         private val local: FireModel,
                                         private val remote: FireModel) {

    private var fires : List<FireParcelable> = emptyList()

    fun getAllFires(onFinished: (List<FireParcelable>) -> Unit) {
        if(ConnectivityUtil.isOnline(context)) {
            remote.getAllFires { history ->
                local.deleteAllOperations {
                    local.insertFires(history) {
                        fires = history
                        onFinished(history)
                    }
                }
            }
        } else {
            local.getAllFires(onFinished)
        }
    }

    //Funções do model anterior ADAPTAR
    //Função pra retornar a lista de Fires da API AINDA NÃO TEM
    fun getAllRegistros(onFinished: (List<FireParcelable>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(fires)
        }
    }

    fun getAllFiresList() : List<FireParcelable>{
        return fires
    }

    //Funções para estatisticas
    fun getActiveFires() : List<FireParcelable>{
        var activeFires = listOf<FireParcelable>()

        for (f in fires) {
            if (f.status == "Em curso") {
                activeFires = activeFires + f
            }
        }

        return activeFires
    }

    fun getFiresInRange(range : Int) : List<FireParcelable>{
        var firesInRange = listOf<FireParcelable>()

        for (f in fires) {
            if (f.status == "Em curso") {
                firesInRange = firesInRange + f
            }
        }

        return firesInRange
    }

    fun getDistrictWithMostFires() : String {
        var firesByDistrict : MutableMap<String, Int> = mutableMapOf<String,Int>()
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
        var firesByDistrict : MutableMap<String, Int> = mutableMapOf<String,Int>()
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
        val activeFires = getActiveFires()
        var totalOperationals = 0

        for(fire in activeFires) {
            totalOperationals += fire.operationals.toInt()
        }

        return totalOperationals;
    }

    fun getTotalVehicles(): Int {
        val activeFires = getActiveFires()
        var totalVehicle = 0

        for(fire in activeFires) {
            totalVehicle += fire.vehicles.toInt()
        }

        return totalVehicle;
    }

    fun getTotalPlanes(): Int {
        val activeFires = getActiveFires()
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