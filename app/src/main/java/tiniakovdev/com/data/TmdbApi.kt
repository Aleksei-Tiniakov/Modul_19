package tiniakovdev.com.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tiniakovdev.com.data.tmdb.TmdbResults

interface TmdbApi {
    @GET("3/movie/popular")
    fun getFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<TmdbResults>
}
