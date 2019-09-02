package ar.com.favio.wefoxpokedex.screens.backpack


import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.favio.wefoxpokedex.R
import ar.com.favio.wefoxpokedex.core.data.model.Pokemon
import ar.com.favio.wefoxpokedex.databinding.BackpackFragmentBinding
import ar.com.favio.wefoxpokedex.screens.backpack.detail.DetailActivity
import ar.com.favio.wefoxpokedex.utils.Backpack

import dagger.android.support.DaggerFragment
import javax.inject.Inject

class BackpackFragment  : DaggerFragment() {

    companion object {
        fun newInstance() = BackpackFragment()
    }

    lateinit var binding: BackpackFragmentBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: BackpackAdapter
    @Inject
    internal lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: BackpackViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.backpack_fragment, container, false)
        val view = binding.root
        initRecyclerView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(BackpackViewModel::class.java)
        getMyPokemons()
    }

    private fun initRecyclerView() {
        adapter = BackpackAdapter(ArrayList()) { pokemon : Pokemon -> pokemonClicked(pokemon) }
        recyclerView = binding.backpackRecyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(context, 4)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
    }

    private fun pokemonClicked(pokemon: Pokemon) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("pokemon", pokemon)
        activity?.startActivity(intent)
    }

    private fun getMyPokemons() {
        binding.progressBar.visibility = View.VISIBLE
        viewModel.fetchPokemons()
        viewModel.getPokemons().observe(this, Observer {
            adapter.updateAdapter(it)
            Backpack.getInstance().pokemons = it
            binding.progressBar.visibility = View.GONE
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}