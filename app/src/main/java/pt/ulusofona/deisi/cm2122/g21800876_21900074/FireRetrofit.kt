package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.content.IntentSender
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Retrofit

class FireRetrofit(retrofit: Retrofit) {
    private val TAG = FireRetrofit::class.java.simpleName
    private val service = retrofit.create(FireService::class.java)

    //Implementar os overrides
    /* override fun getFires(onFinished: (List<FireParcelable>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val fires = service.getAllFires()
                onFinished(fires.map { FireParcelable(
                    it.data.id,
                    it.data.date,
                    it.data.district,
                    it.data.conselho,
                    it.data.frequesia,
                    it.data.status,
                    it.data.man,
                    it.data.vehicles,
                    it.data.planes,
                ) })   
            } catch (ex: HttpException) {
                Log.e(TAG, ex.message())
            }
        }

    }

     */
}