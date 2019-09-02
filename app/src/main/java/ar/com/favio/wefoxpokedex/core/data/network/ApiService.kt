package ar.com.favio.wefoxpokedex.core.data.network

import ar.com.favio.wefoxpokedex.core.data.db.PokemonDTO
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int)
            : Deferred<Response<PokemonDTO>>
}