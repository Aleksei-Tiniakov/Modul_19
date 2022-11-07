package tiniakovdev.com.domain

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tiniakovdev.com.data.API
import tiniakovdev.com.data.MainRepository
import tiniakovdev.com.data.TmdbApi
import tiniakovdev.com.data.tmdb.TmdbResults
import tiniakovdev.com.utils.Converter
import tiniakovdev.com.viewmodul.HomeFragmentViewModel

class Interactor(private val repository: MainRepository, private val retrofitService: TmdbApi) {

    fun getFilmsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getFilms(API.API_KEY, "en-US", page)
            .enqueue(object : Callback<TmdbResults> {
                override fun onResponse(call: Call<TmdbResults>, response: Response<TmdbResults>) {
                    callback.onSuccess(
                        Converter.convertApiListToDtoList(
                            response.body()?.results
                        )
                    )
                }

                override fun onFailure(call: Call<TmdbResults>, t: Throwable) {
                    callback.onFailure()
                }
            })
    }
}