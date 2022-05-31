package pt.ulusofona.deisi.cm2122.g21800876_21900074

import retrofit2.http.*

data class FireContent(
    val id:String,
    val date:String,
    val hour:String,
    val district: String,
    val conselho: String,
    val frequesia: String,
    val status: String,
    val man : String,
    val vehicles : String,
    val planes : String,
    val lat:Int,
    val lng:Int,
)

data class FireData(val data:FireContent)

interface FireService {
    //Pega todos os fogos
    @GET("/new/fires")
    suspend fun getAllFires():List<FireData>

    //@GET("")
    //suspend fun getEspecificFire():FireData
}