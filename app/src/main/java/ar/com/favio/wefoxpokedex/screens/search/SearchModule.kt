package ar.com.favio.wefoxpokedex.screens.search

import androidx.lifecycle.ViewModelProvider
import ar.com.favio.wefoxpokedex.core.data.repositories.Repository
import ar.com.favio.wefoxpokedex.utils.ViewModelProviderFactory
import dagger.Module
import dagger.Provides

@Module
class SearchModule {
    @Provides
    internal fun provideViewModel(repository: Repository): SearchViewModel {
        return SearchViewModel(repository)
    }

    @Provides
    internal fun provideViewModelFactory(viewModel: SearchViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(viewModel)
    }
}