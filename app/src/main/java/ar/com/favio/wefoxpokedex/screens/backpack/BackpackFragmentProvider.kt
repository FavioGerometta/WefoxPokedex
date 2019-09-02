package ar.com.favio.wefoxpokedex.screens.backpack

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BackpackFragmentProvider {
    @ContributesAndroidInjector(modules = arrayOf(BackpackModule::class))
    internal abstract fun contributeBackpackProvider(): BackpackFragment
}