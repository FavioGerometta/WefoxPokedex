package ar.com.favio.futbolguru.core.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ar.com.favio.wefoxpokedex.core.data.db.PokemonsDao
import ar.com.favio.wefoxpokedex.core.data.model.Pokemon
import ar.com.favio.wefoxpokedex.utils.RoomTypeConverters

@Database(entities = arrayOf(Pokemon::class), version = 2, exportSchema = false)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonsDao(): PokemonsDao

    companion object {
        var TEST_MODE = false
        private val databaseName = "pokemons_db"

        private var db: AppDatabase? = null
        private var dbInstance: PokemonsDao? = null

        fun getInstance(context: Context): PokemonsDao {
            if (dbInstance == null) {
                if (TEST_MODE) {
                    db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
                    dbInstance = db?.pokemonsDao()
                } else {
                    db = Room.databaseBuilder(context, AppDatabase::class.java, databaseName).build()
                    dbInstance = db?.pokemonsDao()
                }
            }
            return dbInstance!!;
        }

        private fun close() {
            db?.close()
        }
    }
}