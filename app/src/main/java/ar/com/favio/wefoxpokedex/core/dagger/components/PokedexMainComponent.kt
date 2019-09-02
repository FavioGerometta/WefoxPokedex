package ar.com.favio.wefoxpokedex.core.dagger.components

import ar.com.favio.wefoxpokedex.core.dagger.builders.ActivityBuilder
import ar.com.favio.wefoxpokedex.core.dagger.modules.PokedexMainModule
import ar.com.favio.wefoxpokedex.core.dagger.modules.NetworkModule
import ar.com.favio.wefoxpokedex.PokedexApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AndroidSupportInjectionModule::class,
    PokedexMainModule::class,
    NetworkModule::class,
    ActivityBuilder::class))
interface PokedexMainComponent: AndroidInjector<PokedexApplication> {

    @Component.Builder
    abstract class Builder: AndroidInjector.Builder<PokedexApplication>()
}