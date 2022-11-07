package tiniakovdev.com.di.modules

import dagger.Module
import dagger.Provides
import tiniakovdev.com.data.MainRepository
import tiniakovdev.com.data.TmdbApi
import tiniakovdev.com.domain.Interactor
import javax.inject.Singleton

@Module
class DomainModule {
    @Provides
    @Singleton
    fun provideInteractor(repository: MainRepository, tmdbApi: TmdbApi) =
        Interactor(repository = repository, retrofitService = tmdbApi)
}