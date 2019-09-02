package ar.com.favio.wefoxpokedex.core.dagger.modules

import android.content.Context
import androidx.room.Room
import ar.com.favio.wefoxpokedex.core.data.repositories.Repository
import ar.com.favio.futbolguru.core.data.db.AppDatabase
import ar.com.favio.wefoxpokedex.core.data.db.PokemonsDao
import ar.com.favio.wefoxpokedex.core.data.network.ApiService
import ar.com.favio.wefoxpokedex.PokedexApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PokedexMainModule {

    @Singleton
    @Provides
    internal fun provideContext(application: PokedexApplication): Context {
        return application
    }

    @Provides
    internal fun provideRepository(apiService: ApiService, dao: PokemonsDao): Repository {
        return Repository(apiService, dao)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, "pokemons_db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideCompetitionDao(appDatabase: AppDatabase):
            PokemonsDao = appDatabase.pokemonsDao()
}