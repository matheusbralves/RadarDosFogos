package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.net.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit

/***
 *
 * Classe que transforma a classe da API - FireService em FireParcelable
 * FireRetrofit - API == FireModelRoom - DAO
 *
 * ***/

class FireRetrofit(retrofit: Retrofit) : FireModel() {
    private val TAG = FireRetrofit::class.java.simpleName
    private val service = retrofit.create(FireService::class.java)

    override fun getAllFires(onFinished: (List<FireParcelable>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            //Pega todos os Fire da API
            val fires = service.getAllFires()
            //Transforma em FireParcelable
            onFinished(fires.data.map{
                FireParcelable(it.id, it.district + " / " + it.concelho + " / " + it.freguesia, "", "", it.district,
                    it.concelho, it.freguesia, it.date, it.hour, it.status, "", "",
                    it.man, it.terrain, it.aerial,it.lat, it.lng, "false", "2131230896")
            })
        }
    }

    //Não serve para a API
    override fun addFire(
        nome: String, numeroCC: String, distrito: String, conselho: String, frequesia: String,
        data: String, hora: String, status: String, foto: String, distancia: String,
        operationais: String, veiculos: String, planes: String,
        lat : Double, lng : Double, isRegistry : String, photo : String) {
        TODO("Not yet implemented")
    }

    override fun insertFires(fires: List<FireParcelable>,
                             onFinished: (List<FireParcelable>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun deleteAllOperations(onFinished: (List<FireParcelable>) -> Unit) {
        TODO("Not yet implemented")
    }
}