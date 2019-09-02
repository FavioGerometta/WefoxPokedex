package ar.com.favio.wefoxpokedex.core.dagger.builders

import ar.com.favio.wefoxpokedex.screens.HomeActivity
import ar.com.favio.wefoxpokedex.screens.HomeActivityModule
import ar.com.favio.wefoxpokedex.screens.backpack.BackpackFragmentProvider
import ar.com.favio.wefoxpokedex.screens.search.SearchFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(
        HomeActivityModule::class,
        BackpackFragmentProvider::class,
        SearchFragmentProvider::class))
    internal abstract fun contributeFragmentsActivityModule(): HomeActivity


}