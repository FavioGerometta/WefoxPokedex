package ar.com.favio.wefoxpokedex.screens.backpack.detail

import android.os.Bundle
import ar.com.favio.wefoxpokedex.R
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import ar.com.favio.wefoxpokedex.core.data.model.Pokemon
import ar.com.favio.wefoxpokedex.databinding.ActivityDetailBinding
import ar.com.favio.wefoxpokedex.utils.setImageUrl
import ar.com.favio.wefoxpokedex.utils.visible
import kotlinx.android.synthetic.main.activity_home.*

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.toolbar.title = "Your Pokemon"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initBindings()
    }

    private fun initBindings() {
        val pokemon = intent.getParcelableExtra<Pokemon>("pokemon")
        binding.pokemonBasicContainer.pokemon = pokemon
        binding.pokemonBasicContainer.imageViewPokemon.setImageUrl(pokemon.frontSprite)
        binding.pokemonBasicContainer.textViewPokemonDateTime.visible = true
        //binding.pokemonBasicContainer.textViewPokemonDateTime
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
