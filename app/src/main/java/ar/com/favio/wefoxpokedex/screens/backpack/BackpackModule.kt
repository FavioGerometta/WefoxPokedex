package ar.com.favio.wefoxpokedex.screens.backpack

import androidx.lifecycle.ViewModelProvider
import ar.com.favio.wefoxpokedex.core.data.repositories.Repository

import ar.com.favio.wefoxpokedex.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class BackpackModule {

    @Provides
    internal fun provideViewModel(repository: Repository): BackpackViewModel {
        return BackpackViewModel(repository)
    }

    @Provides
    internal fun provideViewModelFactory(viewModel: BackpackViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}