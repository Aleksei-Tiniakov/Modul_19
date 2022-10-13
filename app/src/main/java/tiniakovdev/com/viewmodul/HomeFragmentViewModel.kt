package tiniakovdev.com.viewmodul

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tiniakovdev.com.App
import tiniakovdev.com.domain.Film
import tiniakovdev.com.domain.Interactor

class HomeFragmentViewModel : ViewModel() {
    val filmsListLiveData: MutableLiveData<List<Film>> = MutableLiveData()
    private var interactor: Interactor = App.instance.interactor

    init {
        val films = interactor.getFilmsDB()
        filmsListLiveData.postValue(films)
    }
}