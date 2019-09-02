package ar.com.favio.wefoxpokedex.utils

import ar.com.favio.wefoxpokedex.core.data.model.Pokemon

class Backpack {    private constructor()
    //TODO: change in the future! late init inside companion throws error
    // , apparently something that will be fixed in later versions of Kotlin ¯\_(ツ)_/¯
    companion object {
        @Volatile private var INSTANCE: Backpack? = null
        fun getInstance(): Backpack =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Backpack().also { INSTANCE = it }
            }
    }

    private var _pokemons : List<Pokemon> = emptyList()
    var pokemons: List<Pokemon> = _pokemons


}