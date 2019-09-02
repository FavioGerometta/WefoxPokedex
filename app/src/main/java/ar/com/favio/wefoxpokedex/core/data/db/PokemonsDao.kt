package ar.com.favio.wefoxpokedex.core.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.com.favio.wefoxpokedex.core.data.model.Pokemon

@Dao
interface PokemonsDao : BaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: Pokemon)

    @Query("SELECT * FROM pokemons ORDER BY pokemons.`order`")
    suspend fun queryPokemons(): List<Pokemon>

    @Query("SELECT * FROM pokemons WHERE pokemons.id = :id")
    suspend fun queryPokemon(id : Int): Pokemon

    @Query("DELETE FROM pokemons")
    override fun flushData()

    @Query("SELECT count(*) FROM pokemons")
    override fun getItemCount(): Int

}