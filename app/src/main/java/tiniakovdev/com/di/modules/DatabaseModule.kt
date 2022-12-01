package tiniakovdev.com.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tiniakovdev.com.data.AppDatabase
import tiniakovdev.com.data.MainRepository
import tiniakovdev.com.data.dao.FilmDao
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseHelper(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "film_db"
        ).build().filmDao()

    @Provides
    @Singleton
    fun provideRepository(filmDao: FilmDao) = MainRepository(filmDao)
}