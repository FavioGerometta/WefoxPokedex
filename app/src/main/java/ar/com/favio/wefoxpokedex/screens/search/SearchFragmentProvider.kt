package ar.com.favio.wefoxpokedex.screens.search

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchFragmentProvider {
    @ContributesAndroidInjector(modules = arrayOf(SearchModule::class))
    internal abstract fun contributeBackpackProvider(): SearchFragment
}