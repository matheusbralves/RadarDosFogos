package pt.ulusofona.deisi.cm2122.g21800876_21900074

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/***
 *
 * Pega o endereço da API e le o JSON
 *
 * ***/

object RetrofitBuilder {
    fun getInstance(path:String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(path)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}