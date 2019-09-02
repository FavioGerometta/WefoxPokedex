package ar.com.favio.wefoxpokedex.screens.backpack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ar.com.favio.wefoxpokedex.core.data.model.Pokemon
import ar.com.favio.wefoxpokedex.R
import ar.com.favio.wefoxpokedex.databinding.PokemonListItemBinding
import ar.com.favio.wefoxpokedex.utils.setImageUrl

class BackpackAdapter(
    private var pokemonList: List<Pokemon>,
    val clickListener: (Pokemon) -> Unit
): RecyclerView.Adapter<BackpackAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.pokemon_list_item, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.binding?.pokemon = pokemon
        holder.binding?.imageViewPokemon?.setImageUrl(pokemon.frontSprite)
        holder.binding?.imageViewPokemon?.setOnClickListener{
            clickListener(pokemon)
        }
    }

    fun updateAdapter(value: List<Pokemon>){
        pokemonList = value
        return notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding: PokemonListItemBinding? = DataBindingUtil.bind(view)
        init {
            view.tag = binding
        }
    }
}

