package ar.com.favio.wefoxpokedex.screens.search


import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ar.com.favio.wefoxpokedex.R
import ar.com.favio.wefoxpokedex.core.data.model.Pokemon
import ar.com.favio.wefoxpokedex.databinding.SearchFragmentBinding
import ar.com.favio.wefoxpokedex.utils.Backpack
import ar.com.favio.wefoxpokedex.utils.setImageUrl
import ar.com.favio.wefoxpokedex.utils.visible
import dagger.android.support.DaggerFragment
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SearchFragment  : DaggerFragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }
    lateinit var binding: SearchFragmentBinding
    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: SearchViewModel
    private lateinit var currentPokemon: Pokemon

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        val view = binding.root
        configureListeners()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
        binding.pokemonContainer.pokemonBasicContainer.textViewPokemonHeight.visible = false
    }

    private fun configureListeners() {
        binding.pokemonContainer.buttonCatch.setOnClickListener {
            if(Backpack.getInstance().pokemons.contains(currentPokemon)){
                Toast.makeText(context, getString(R.string.search_error_repeated), Toast.LENGTH_SHORT)
            }else {
                val date = getCurrentDateTime()
                val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")
                currentPokemon.dateTime = dateInString
                viewModel.insertInDb(currentPokemon)
                getPokemon()
            }
        }
        binding.pokemonContainer.buttonLeave.setOnClickListener {
            getPokemon()
        }
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun getPokemon() {
        binding.pokemonContainer.buttonLeave.text = getString(R.string.leave_pokemon)
        viewModel.canFetchData().observe(this, Observer {
            toggleInternetIndicators(it)
        })
        binding.progressBarPokemonLoading.visible = true
        viewModel.fetchPokemon((1..1000).shuffled().first())
        viewModel.getPokemon().observe(this, Observer {
            binding.progressBarPokemonLoading.visible = false
            if(it.pokemon!=null){
                currentPokemon = it.pokemon
                binding.pokemonContainer.pokemonBasicContainer.pokemon = it.pokemon
                binding.pokemonContainer.pokemonBasicContainer.imageViewPokemon.setImageUrl(it.pokemon.frontSprite)
                //toggle visibility
                binding.pokemonContainer.pokemonBasicContainer.textViewPokemonName.visible = true
                binding.pokemonContainer.pokemonBasicContainer.textViewPokemonHeight.visible = true
                binding.pokemonContainer.buttonContainer.visible = true
                binding.pokemonContainer.buttonCatch.visible = true
                binding.progressBarPokemonLoading.visible = false

            }else{

                val toast = Toast.makeText(context, it.exception?.message,Toast.LENGTH_SHORT)
                toast.show()
                Handler().postDelayed( { toast.cancel()},800)
                //null image url goes into default for glide
                binding.pokemonContainer.pokemonBasicContainer.imageViewPokemon.setImageUrl(null)
                //toggle visibility
                binding.pokemonContainer.pokemonBasicContainer.textViewPokemonName.visible = false
                binding.pokemonContainer.pokemonBasicContainer.textViewPokemonHeight.visible = false
                binding.pokemonContainer.buttonContainer.visible = true
                binding.pokemonContainer.buttonCatch.visible =false
                binding.pokemonContainer.buttonLeave.visible =true
            }
        })
    }

    private fun toggleInternetIndicators(flag:Boolean) {
        binding.progressBarPokemonLoading.visible = flag
        if(!flag){
            val toast = Toast.makeText(context, getString(R.string.search_error_no_internet), Toast.LENGTH_SHORT)
            toast.show()
            Handler().postDelayed( { toast.cancel()},800)
            //toggle visibility
            binding.pokemonContainer.buttonContainer.visible = true
            binding.pokemonContainer.buttonCatch.visible =false
        }
    }
}