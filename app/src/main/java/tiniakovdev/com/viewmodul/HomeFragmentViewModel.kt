package tiniakovdev.com.viewmodul

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tiniakovdev.com.App
import tiniakovdev.com.data.entity.Film
import tiniakovdev.com.domain.Interactor
import tiniakovdev.com.utils.SingleLiveEvent
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val showProgressBar: MutableLiveData<Boolean> = MutableLiveData()
    private val uploadData = SingleLiveEvent<List<Film>>()

    fun getLoadData(): SingleLiveEvent<List<Film>> {
        return uploadData
    }

    @Inject
    lateinit var interactor: Interactor
    val filmsListLiveData: LiveData<List<Film>>

    init {
        App.instance.dagger.inject(this)
        filmsListLiveData = interactor.getFilmFromDB()
        getFilms()
    }

    fun getFilms() {
        showProgressBar.postValue(true)
        interactor.getFilmsFromApi(1, object : ApiCallback {
            override fun onSuccess() {
                showProgressBar.postValue(false)
            }

            override fun onFailure() {
                showProgressBar.postValue(false)
            }
        })
    }

    interface ApiCallback {
        fun onSuccess()
        fun onFailure()
    }
}

