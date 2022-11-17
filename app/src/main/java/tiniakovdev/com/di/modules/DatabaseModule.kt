package tiniakovdev.com.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import tiniakovdev.com.data.MainRepository
import tiniakovdev.com.data.db.DatabaseHelper
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseHelper(context: Context) = DatabaseHelper(context)

    @Provides
    @Singleton
    fun provideRepository(databaseHelper: DatabaseHelper) = MainRepository(databaseHelper)
}