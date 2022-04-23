package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.net.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//private val TAG = MainActivity::class.java.simpleName

object FireModel {

    val registros = mutableListOf(
        FireParcelable("test 1", "11111111", "Setubal","Barreiro", "Lavradio",
        "22/04/2022", "14:15", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "25",
            "1", "1", "1"),

        FireParcelable("test 2", "22222222", "Lisboa","Lumiar", "Lumiar",
        "24/04/2022", "12:10", "Terminado", Uri.parse(R.drawable.sem_foto.toString()), "25",
            "2", "2", "2"),

        FireParcelable("test 3", "33333333", "Setubal","Moita", "Ronda",
        "25/04/2022", "10:25", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "35",
            "3", "3", "3"),

        FireParcelable("test 4", "44444444", "Aveiro","Agueda", "Trofa",
            "25/04/2022", "10:25", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "50",
            "4", "4", "4"),

        FireParcelable("test 5", "55555555", "Porto","Valongo", "Campo",
            "25/04/2022", "10:25", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "50",
            "5", "5", "5"),

        FireParcelable("test 5", "55555555", "Porto","Valongo", "Campo",
            "25/04/2022", "10:25", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "50",
            "5", "5", "5"),

        FireParcelable("test 5", "55555555", "Porto","Valongo", "Campo",
            "25/04/2022", "10:25", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "50",
            "5", "5", "5"),

        FireParcelable("test 5", "55555555", "Porto","Valongo", "Campo",
            "25/04/2022", "10:25", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "50",
            "5", "5", "5"),

        FireParcelable("test 5", "55555555", "Porto","Valongo", "Campo",
            "25/04/2022", "10:25", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "50",
            "5", "5", "5"),
    )

    private val list25 = mutableListOf(
        FireParcelable("test 1", "11111111", "Setubal","Barreiro", "Lavradio",
            "22/04/2022", "14:15", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "25",
            "1", "1", "1"),

        FireParcelable("test 2", "22222222", "Lisboa","Lumiar", "Lumiar",
            "24/04/2022", "12:10", "Terminado", Uri.parse(R.drawable.sem_foto.toString()), "25",
            "2", "2", "2"),
    )

    private val list35 = mutableListOf(
        FireParcelable("test 3", "33333333", "Setubal","Moita", "Ronda",
            "25/04/2022", "10:25", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "35",
            "3", "3", "3"),
    )

    private val list50 = mutableListOf(
        FireParcelable("test 4", "44444444", "Aveiro","Agueda", "Trofa",
            "25/04/2022", "10:25", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "50",
            "4", "4", "4"),

        FireParcelable("test 5", "55555555", "Porto","Valongo", "Campo",
            "25/04/2022", "10:25", "Em curso", Uri.parse(R.drawable.sem_foto.toString()), "50",
            "5", "5", "5"),
    )

    fun addRegistro(nome : String,
                    numeroCC : String,
                    distrito : String,
                    conselho : String,
                    frequesia : String,
                    data : String,
                    hora : String,
                    status : String,
                    foto : Uri?,
                    distancia : String,
                    operationais : String,
                    veiculos : String,
                    planes : String,
    )

    {
        foto?.let {
            FireParcelable(nome, numeroCC, distrito, conselho, frequesia, data, hora, status,
                foto, distancia, operationais, veiculos, planes
            )
        }?.let { registros.add(it) }
    }

    fun getAllRegistros(onFinished: (List<FireParcelable>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(registros)
        }
    }

    fun getDashboardRegistros25(onFinished: (List<FireParcelable>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(list25)
        }
    }

    fun getDashboardRegistros35(onFinished: (List<FireParcelable>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(list35)
        }
    }

    fun getDashboardRegistros50(onFinished: (List<FireParcelable>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            onFinished(list50)
        }
    }

    fun getActiveFires() : List<FireParcelable>{
        var activeFires = listOf<FireParcelable>()

        for (f in registros) {
            if (f.status == "Em curso") {
                activeFires = activeFires + f
            }
        }

        return activeFires
    }

    fun getFiresInRange(range : Int) : List<FireParcelable>{
        var firesInRange = listOf<FireParcelable>()

        for (f in registros) {
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

        for (f in registros) {
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

        for (f in registros) {
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

}