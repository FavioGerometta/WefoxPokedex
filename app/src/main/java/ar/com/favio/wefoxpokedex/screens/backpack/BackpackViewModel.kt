package ar.com.favio.wefoxpokedex.screens.backpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ar.com.favio.wefoxpokedex.core.data.repositories.Repository
import ar.com.favio.wefoxpokedex.core.data.model.Pokemon
import ar.com.favio.wefoxpokedex.core.vm.BaseViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject
class BackpackViewModel @Inject constructor(
    private val repository: Repository
) : BaseViewModel() {

    val pokemonsLiveData = MutableLiveData<List<Pokemon>>()
    fun getPokemons(): LiveData<List<Pokemon>> = pokemonsLiveData
    fun fetchPokemons(){
        scope.launch {
            val pokemons = repository.getMyPokemonsFromDb()
            pokemonsLiveData.postValue(pokemons)
        }
    }

    override fun onCleared() {
        super.onCleared()
        cancelAllRequests()
    }
}