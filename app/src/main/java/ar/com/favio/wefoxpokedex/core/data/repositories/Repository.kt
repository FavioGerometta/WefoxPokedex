package ar.com.favio.wefoxpokedex.core.data.repositories

import ar.com.favio.wefoxpokedex.core.data.db.PokemonDTO
import ar.com.favio.wefoxpokedex.core.data.db.PokemonsDao
import ar.com.favio.wefoxpokedex.core.data.model.Pokemon
import ar.com.favio.wefoxpokedex.core.data.model.Types
import ar.com.favio.wefoxpokedex.core.data.network.ApiService
import retrofit2.HttpException
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val dao: PokemonsDao
) : BaseRepository() {

    suspend fun getMyPokemonsFromDb() :List<Pokemon>?{
        return dao.queryPokemons()
    }

    suspend fun getPokemon(id:Int) :PokemonResponseWrapper{
        try {
            val response = apiService.getPokemon(id).await()
            if(response.isSuccessful)
                return PokemonResponseWrapper(response.body()!!.toPokemon(), null)
            else {
                return when (response.code()){ //don't have enough info or time to check on other exceptions TODO: add support for other HTTP Status codes
                    404 -> PokemonResponseWrapper(null, PokemonNotFoundException("Pokemon not found! : ${response.code()}"))
                    else -> PokemonResponseWrapper(null ,PokemonUnknownException("Unmapped exception code: ${response.code()}"))
                }
            }
        }catch (e: HttpException){
            return PokemonResponseWrapper(null, e)
        }
        catch (e: Throwable) {
            return PokemonResponseWrapper(null,PokemonUnknownException(e.localizedMessage))
        }
    }

    suspend fun insert(pokemon:Pokemon) {
        return dao.insert(pokemon)
    }
}

private fun PokemonDTO.toPokemon()= Pokemon(
     id = id,
     base_experience = base_experience,
     height=height,
     weight=weight,
     name=name,
     order=order,
     frontSprite=sprites?.front_default,
     types= Types(types?.map { it.type.name }),
     dateTime= ""
)

class PokemonUnknownException(message:String): Exception(message)
class PokemonNotFoundException(message:String): Exception(message)
data class PokemonResponseWrapper(val pokemon: Pokemon?, val exception:Exception?)