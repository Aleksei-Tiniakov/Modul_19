package tiniakovdev.com.data

import androidx.lifecycle.LiveData
import tiniakovdev.com.data.dao.FilmDao
import tiniakovdev.com.data.entity.Film
import java.util.concurrent.Executors

class MainRepository(private val filmDao: FilmDao) {

    fun putToDb(films: List<Film>) {
        Executors.newSingleThreadExecutor().execute {
            filmDao.insertAll(films)
        }
    }

    fun getAllFromDB(): LiveData<List<Film>> = filmDao.getCachedFilms()
}