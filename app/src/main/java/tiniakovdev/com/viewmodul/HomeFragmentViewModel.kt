package tiniakovdev.com.viewmodul

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tiniakovdev.com.App
import tiniakovdev.com.domain.Film
import tiniakovdev.com.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData: MutableLiveData<List<Film>> = MutableLiveData()

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        getFilms()
    }

    fun getFilms() {
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess(film: List<Film>) {
                filmsListLiveData.postValue(film)
            }

            override fun onFailure() {
                filmsListLiveData.postValue(interactor.getFilmFromDB())
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(film: List<Film>)
        fun onFailure()
    }
}