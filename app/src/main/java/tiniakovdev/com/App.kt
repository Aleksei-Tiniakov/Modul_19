package tiniakovdev.com

import android.app.Application
import tiniakovdev.com.data.MainRepository
import tiniakovdev.com.domain.Interactor

class App : Application() {
    lateinit var repository: MainRepository
    lateinit var interactor: Interactor

    override fun onCreate() {
        super.onCreate()
        instance = this
        repository = MainRepository()
        interactor = Interactor(repository)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}