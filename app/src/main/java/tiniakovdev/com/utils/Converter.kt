package tiniakovdev.com.utils

import tiniakovdev.com.data.entity.Film
import tiniakovdev.com.data.tmdb.TmdbFilm

object Converter {
    fun convertApiListToDtoList(list: List<TmdbFilm>?): List<Film> {
        val result = mutableListOf<Film>()
        list?.forEach {
            result.add(
                Film(
                    title = it.title,
                    poster = it.posterPath,
                    description = it.overview,
                    rating = it.voteAverage,
                    isInFavorites = false,
                    id = it.id
                )
            )
        }
        return result
    }
}