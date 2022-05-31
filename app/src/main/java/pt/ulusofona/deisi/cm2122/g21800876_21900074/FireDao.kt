package pt.ulusofona.deisi.cm2122.g21800876_21900074

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FireDao {
    @Insert
    suspend fun insert(fire: FireRoom)

    @Query("SELECT * FROM fire")
    suspend fun getAll(): List<FireRoom>
}