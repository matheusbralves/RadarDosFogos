package pt.ulusofona.deisi.cm2122.g21800876_21900074

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FireModelRoom(private val dao: FireDao) : FireModel() {

    override fun addFire(
        nome: String,
        numeroCC: String,
        distrito: String,
        conselho: String,
        frequesia: String,
        data: String,
        hora: String,
        status: String,
        foto: String,
        distancia: String,
        operationais: String,
        veiculos: String,
        planes: String
    ) {
        val fire = FireRoom(fire_name = "", nome = nome, numeroCC = numeroCC, distrito = distrito,
            conselho = conselho, frequesia = frequesia, data = data, hora = hora, status = status,
            foto = foto, distancia = distancia, operationais = operationais, veiculos = veiculos,
            planes = planes)

        CoroutineScope(Dispatchers.IO).launch { dao.insert(fire) }
    }

    override fun getAllFires(onFinished: (List<FireParcelable>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val fires = dao.getAll()
            onFinished(fires.map{
                FireParcelable(it.uuid, it.fire_name, it.nome, it.numeroCC, it.distrito,
                    it.conselho, it.frequesia, it.data, it.hora, it.status, it.foto, it.distancia,
                    it.operationais, it.veiculos, it.planes,0.0,0.0)
            })
        }
    }

    override fun insertFires(fires: List<FireParcelable>, onFinished: (List<FireParcelable>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val history = fires.map { FireRoom(it.uuid, it.fire_name, it.nome, it.numeroCC, it.distrito,
                it.conselho, it.frequesia, it.data, it.hora, it.status, it.foto, it.distancia,
                it.operationals, it.vehicles, it.planes) }
            dao.insertAll(history)
            onFinished(fires)
        }
    }

    override fun deleteAllOperations(onFinished: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteAll()
            onFinished()
        }
    }
}