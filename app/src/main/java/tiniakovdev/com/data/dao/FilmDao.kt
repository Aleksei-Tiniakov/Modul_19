package tiniakovdev.com.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tiniakovdev.com.data.entity.Film

@Dao
interface FilmDao {

    @Query("SELECT * FROM cached_films")
    fun getCachedFilms(): LiveData<List<Film>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Film>)
}