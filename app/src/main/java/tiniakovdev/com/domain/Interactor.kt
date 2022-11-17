package tiniakovdev.com.domain

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tiniakovdev.com.data.API
import tiniakovdev.com.data.MainRepository
import tiniakovdev.com.data.PreferenceProvider
import tiniakovdev.com.data.TmdbApi
import tiniakovdev.com.data.tmdb.TmdbResults
import tiniakovdev.com.utils.Converter
import tiniakovdev.com.viewmodul.HomeFragmentViewModel

class Interactor(
    private val retrofitService: TmdbApi,
    val repository: MainRepository,
    val preferences: PreferenceProvider
) {

    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(getDefaultCategoryFromPreferences(), API.API_KEY, "ru-RU", page)
            .enqueue(object : Callback<TmdbResults> {
                override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                    val list = Converter.convertApiListToDtoList(response.body()?.results)
                    list.forEach {
                        repository.putToDb(film = it)
                    }
                    callback.onSuccess(list)
                }

                override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                    callback.onFailure()
                }
            })
    }

    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }
    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getFilmFromDB(): List<Film> = repository.getAllFromDB()
}