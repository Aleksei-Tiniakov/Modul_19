package tiniakovdev.com.domain

import tiniakovdev.com.data.MainRepository

class Interactor(val repository: MainRepository) {

    fun getFilmsDB(): List<Film> = repository.filmsDataBase
}