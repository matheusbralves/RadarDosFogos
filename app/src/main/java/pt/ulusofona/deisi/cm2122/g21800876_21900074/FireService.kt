package pt.ulusofona.deisi.cm2122.g21800876_21900074

import retrofit2.http.*

/***
 *
 * Classe da API
 * Caso precise de mais variáveis mudar aqui + FireParcelable + FireRetrofit
 * FireService - API == FireRoom - DAO
 *
 * ***/

data class FireContent(
    val id:String,
    val date:String,
    val hour:String,
    val district: String,
    val concelho: String,
    val freguesia: String,
    val status: String,
    val man : String,
    val terrain : String,
    val aerial : String,
    val lat:Double,
    val lng:Double,
)

data class FireData(val data:List<FireContent>)

interface FireService {
    //Pega todos os fogos
    @GET("/new/fires")
    suspend fun getAllFires():FireData

    //@GET("")
    //suspend fun getEspecificFire():FireData
}