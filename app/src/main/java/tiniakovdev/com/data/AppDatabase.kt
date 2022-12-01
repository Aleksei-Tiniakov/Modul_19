package tiniakovdev.com.data

import androidx.room.Database
import androidx.room.RoomDatabase
import tiniakovdev.com.data.dao.FilmDao
import tiniakovdev.com.data.entity.Film

@Database(entities = [Film::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}