package ar.com.favio.wefoxpokedex


import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import ar.com.favio.futbolguru.core.data.db.AppDatabase
import ar.com.favio.wefoxpokedex.core.data.db.PokemonsDao
import ar.com.favio.wefoxpokedex.core.data.job.JobProvider
import ar.com.favio.wefoxpokedex.core.data.model.Pokemon
import ar.com.favio.wefoxpokedex.core.data.model.Types
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RoomDBTest : JobProvider {
    private var pokemonsDao: PokemonsDao? = null

    @Before
    fun setup() {
        AppDatabase.TEST_MODE = true

        pokemonsDao = AppDatabase.getInstance(InstrumentationRegistry.getInstrumentation().targetContext)
        pokemonsDao?.flushData()
    }

    @After
    fun tearDown() {
        pokemonsDao?.flushData()
    }

    @Test
    fun should_Insert_Pokemon_Item() {
        val pokemon = Pokemon(-1,"2",3,4,"5",6,7, Types(listOf("8","9")), "10")
        fun testEqualityIsMaintained(){
            scope.launch {
                pokemonsDao?.insert(pokemon)
                val pokemonTest = pokemonsDao?.queryPokemons()?.first{ it.id == -1}!!
                assertEquals(pokemon, pokemonTest)
            }
        }
        testEqualityIsMaintained()
    }

    @Test
    fun should_Flush_All_Data(){
        pokemonsDao?.flushData()
        assertEquals(pokemonsDao?.getItemCount(), 0)
    }
}
