package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.content.IntentSender
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit

class FireRetrofit(retrofit: Retrofit) : FireModel() {
    private val TAG = FireRetrofit::class.java.simpleName
    private val service = retrofit.create(FireService::class.java)

    //Implementar os overrides
    override fun addFire(
        nome: String, numeroCC: String, distrito: String, conselho: String, frequesia: String,
        data: String, hora: String, status: String, foto: String, distancia: String,
        operationais: String, veiculos: String, planes: String) {
        TODO("Not yet implemented")
    }

    override fun getAllFires(onFinished: (List<FireParcelable>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun insertFires(fires: List<FireParcelable>,
                             onFinished: (List<FireParcelable>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun deleteAllOperations(onFinished: () -> Unit) {
        TODO("Not yet implemented")
    }
}