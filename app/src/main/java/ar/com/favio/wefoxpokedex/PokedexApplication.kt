package ar.com.favio.wefoxpokedex

import ar.com.favio.wefoxpokedex.core.dagger.components.DaggerPokedexMainComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PokedexApplication: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerPokedexMainComponent.builder().create(this)
    }
}