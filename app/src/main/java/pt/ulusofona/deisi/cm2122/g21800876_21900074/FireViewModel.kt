package pt.ulusofona.deisi.cm2122.g21800876_21900074

import androidx.lifecycle.ViewModel

class FireViewModel : ViewModel() {

    private val model = FireModel

    fun onGetListDisplay(onFinished: (List<FireParcelable>) -> Unit){
        model.getAllRegistros(onFinished)
    }

    fun getDashboardRegistros25(onFinished: (List<FireParcelable>) -> Unit){
        model.getDashboardRegistros25(onFinished)
    }

    fun getDashboardRegistros35(onFinished: (List<FireParcelable>) -> Unit){
        model.getDashboardRegistros35(onFinished)
    }

    fun getDashboardRegistros50(onFinished: (List<FireParcelable>) -> Unit){
        model.getDashboardRegistros50(onFinished)
    }

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