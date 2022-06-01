package pt.ulusofona.deisi.cm2122.g21800876_21900074

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "fire")
data class FireRoom(
    @PrimaryKey val uuid: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "fire") val fire_name: String,
    var nome: String,
    var numeroCC: String,
    var distrito: String,
    var conselho: String,
    var frequesia: String,
    var data: String,
    var hora: String,
    var status: String,
    var foto: String,
    var distancia : String,
    var operationais : String,
    var veiculos : String,
    var planes : String,
) {

    override fun toString(): String {
        return "Fire(nome='$nome', " +
                "numeroCC='$numeroCC', " +
                "distrito='$distrito', " +
                "data='$data', " +
                "hora='$hora', " +
                "conselho='$conselho', " +
                "frequesia='$frequesia', " +
                "status='$status', " +
                "foto='$foto' " +
                "distancia='$distancia', " +
                "operationais='$operationais', " +
                "veiculos='$veiculos', " +
                "planes='$planes')"
    }

}