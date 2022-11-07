package tiniakovdev.com

import android.app.Application
import tiniakovdev.com.di.AppComponent
import tiniakovdev.com.di.DaggerAppComponent


class App : Application() {

    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.create()

    }

    companion object {
        lateinit var instance: App
            private set
    }
}
