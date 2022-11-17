package tiniakovdev.com.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import tiniakovdev.com.data.MainRepository
import tiniakovdev.com.data.PreferenceProvider
import tiniakovdev.com.data.TmdbApi
import tiniakovdev.com.domain.Interactor
import javax.inject.Singleton

@Module
class DomainModule(val context: Context) {
    @Provides
    fun provideContext() = context


    @Singleton
    @Provides
    fun providePreferences(context: Context) = PreferenceProvider(context)


    @Singleton
    @Provides
    fun provideInteractor(
        repository: MainRepository,
        tmdbApi: TmdbApi,
        preferenceProvider: PreferenceProvider
    ) = Interactor(
        repository = repository,
        retrofitService = tmdbApi,
        preferences = preferenceProvider
    )
}