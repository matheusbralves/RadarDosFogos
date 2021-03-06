package pt.ulusofona.deisi.cm2122.g21800876_21900074

import android.net.Uri

/***
 *
 * Classe padrão para FireRetrofit e FireModelRoom
 * Criar definição das funões abstratas para depois implementar no Retrofit e no ModelRoom
 * Não necessáriamente precisa implementar as funções nos 2 (Deixar como TODO)
 *
 * ***/

abstract class FireModel {
    //Funções utilizadas em FireRoomModel e FireRetrofit
    abstract fun addFire(nome : String, numeroCC : String, distrito : String,
                         conselho : String, frequesia : String, data : String, hora : String,
                         status : String, foto : String, distancia : String, operationais : String,
                         veiculos : String, planes : String,
                         lat : Double, lng : Double, isRegistry : String, photo : String
    )
    abstract fun getAllFires(onFinished: (List<FireParcelable>) -> Unit)
    abstract fun insertFires(fires: List<FireParcelable>, onFinished: (List<FireParcelable>) -> Unit)
    abstract fun deleteAllOperations(onFinished: (List<FireParcelable>) -> Unit)
}