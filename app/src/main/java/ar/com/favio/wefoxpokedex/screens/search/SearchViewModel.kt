package ar.com.favio.wefoxpokedex.screens.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ar.com.favio.wefoxpokedex.core.data.model.Pokemon
import ar.com.favio.wefoxpokedex.core.data.repositories.PokemonResponseWrapper
import ar.com.favio.wefoxpokedex.core.data.repositories.Repository
import ar.com.favio.wefoxpokedex.core.vm.BaseViewModel
import ar.com.favio.wefoxpokedex.utils.Utilities
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {
    private val pokemonLiveData = MutableLiveData<PokemonResponseWrapper>()
    private val noConnectionLiveData = MutableLiveData<Boolean>()
    fun canFetchData(): LiveData<Boolean> = noConnectionLiveData
    fun getPokemon(): LiveData<PokemonResponseWrapper> = pokemonLiveData
    fun fetchPokemon(id:Int){
        scope.launch {
            if(Utilities.hasInternetConnection()){ //TODO: could add exception handling here
                noConnectionLiveData.postValue(true)
                val pokemonResponseWrapper = repository.getPokemon(id)
                pokemonLiveData.postValue(pokemonResponseWrapper)
            }else{
                noConnectionLiveData.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }

    fun insertInDb(pokemon: Pokemon) {
        scope.launch {
            repository.insert(pokemon)
        }

    }
}